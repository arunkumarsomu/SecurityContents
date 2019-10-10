package trivera.security.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
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

public class EmailQueryController extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Pattern userNamePat = null;

	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);
		String userNamePattern = getInitParameter("userNamePattern");
		userNamePat = Pattern.compile(userNamePattern);
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = (String) req.getParameter("username");
		if (username == null || username.length() < 6 || username.length() > 20) {
			genBadIdPage("Please check your input", req, resp);
			return;
		}

		Matcher patternMatcher = userNamePat.matcher(username);

		if (patternMatcher.find()) {
			genBadIdPage("Please check your input", req, resp);
			return;
		}

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			resp.sendRedirect("index.html");
			return;
		}

		ResultSet rs = null;
		Vector collector = new Vector();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("select EMAIL from MEMBER WHERE USERNAME = ?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numcols = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < numcols; i++) {
					String temp = rs.getString(i + 1);
					System.out.println(temp);
					collector.add(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("index.html");
			return;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = pstmt;
			ConnectionFactory.closeConnection(conn, statements);
		}
		req.setAttribute("results", collector);
		req.getRequestDispatcher("JSPs/EmailResults.jsp").forward(req,
				resp);
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
