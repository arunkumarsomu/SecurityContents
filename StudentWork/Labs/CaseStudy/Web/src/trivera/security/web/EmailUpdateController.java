package trivera.security.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Copyright (c) 2018 Kim Morello
 * </p>
 * @author Kim Morello.
 */

public class EmailUpdateController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = (String) request.getParameter("username");
		String email = (String) request.getParameter("email");

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			response.sendRedirect("index.html");
			return;
		}
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "UPDATE MEMBER SET EMAIL = '" + email
					+ "' WHERE USERNAME = '" + username + "'";
			System.out.println(sql);
			sqlStatement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("index.html");
			return;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}

		request.setAttribute("username", username);
		request.setAttribute("email", email);
		request.getRequestDispatcher("JSPs/EmailUpdateSuccess.jsp").forward(
				request, response);
	}
}
