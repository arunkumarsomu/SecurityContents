package trivera.security.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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

public class EmailQueryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = (String) request.getParameter("username");

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			response.sendRedirect("index.html");
			return;
		}

		ResultSet rs = null;
		Vector collector = new Vector();
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "select EMAIL from MEMBER WHERE USERNAME = '" + username
					+ "'";
			System.out.println(sql);
			rs = sqlStatement.executeQuery(sql);
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
			response.sendRedirect("index.html");
			return;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}
		request.setAttribute("results", collector);
		request.getRequestDispatcher("JSPs/EmailResults.jsp").forward(request,
				response);
	}
}
