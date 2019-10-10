package trivera.security.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trivera.security.data.ConnectionFactory;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * </p>
 * @author Kim Morello.
 */

public class EmailUpdateController extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Pattern userNamePat = null;

	private Pattern emailPat = null;

	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);
		String userNamePattern = getInitParameter("userNamePattern");
		userNamePat = Pattern.compile(userNamePattern);
		String emailPattern = getInitParameter("emailPattern");
		emailPat = Pattern.compile(emailPattern);

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user = (String) req.getParameter("username");
		if (user == null || user.length() < 6 || user.length() > 20) {
			genBadIdPage("Not a legal username", req, resp);
			return;
		}

		Matcher patternMatcher = userNamePat.matcher(user);

		if (patternMatcher.find()) {
			genBadIdPage("Not a legal username", req, resp);
			return;
		}

		String email = (String) req.getParameter("email");

		if (email == null || email.length() < 6 || email.length() > 20) {
			genBadIdPage("Not a legal email", req, resp);
			return;
		}

		patternMatcher = emailPat.matcher(email);

		if (!patternMatcher.find()) {
			genBadIdPage("Not a legal email", req, resp);
			return;
		}

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			resp.sendRedirect("index.html");
			return;
		}
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "UPDATE MEMBER SET EMAIL = '" + email
					+ "' WHERE USERNAME = '" + user + "'";
			System.out.println(sql);
			sqlStatement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("index.html");
			return;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}

		req.setAttribute("username", user);
		req.setAttribute("email", email);
		req.getRequestDispatcher("JSPs/EmailUpdateSuccess.jsp").forward(
				req, resp);
	}

	/** ***************************************************************** */
	/** ***********************Utilities********************************* */
	/** ***************************************************************** */

	private void genBadIdPage(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("ErrorHandler").forward(request, response);
	}

}
