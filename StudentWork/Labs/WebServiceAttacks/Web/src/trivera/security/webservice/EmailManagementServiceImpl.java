package trivera.security.webservice;

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
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * http://www.triveratech.com   
 * </p>
 * @author Trivera Technologies Tech Team.
 */

public class EmailManagementServiceImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String getEmail(String username) {

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			return "Failed";
		}

		ResultSet rs = null;
		StringBuffer collector = new StringBuffer();
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "select EMAIL from MEMBER WHERE USERNAME = '"
					+ username + "'";
			System.out.println(sql);
			rs = sqlStatement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int numcols = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < numcols; i++) {
					String temp = rs.getString(i + 1);
					System.out.println(temp);
					collector.append(temp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Failed";
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}
		return collector.toString();
	}

	public String updateEmail(String username, String email) {

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			return "Failed";
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
			return "Failed";
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}
		
		return "Success";

	}

}
