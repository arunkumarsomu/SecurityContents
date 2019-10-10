package trivera.security.web;


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

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.*;

import trivera.security.data.ConnectionFactory;
import trivera.security.data.User;

public class UserUpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Pattern userNamePat = null;
	private Pattern namePat = null;
	private Pattern streetPat = null;
	private Pattern cityPat = null;
	private Pattern zipPat = null;
	private Pattern ccnPat = null;

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *           if an error occure
	 */
	public void init() throws ServletException {
		String userNamePattern = getInitParameter("userNamePattern");
		userNamePat = Pattern.compile(userNamePattern);
		String namePattern = getInitParameter("namePattern");
		namePat = Pattern.compile(namePattern);
		String streetPattern = getInitParameter("streetPattern");
		streetPat = Pattern.compile(streetPattern);
		String cityPattern = getInitParameter("cityPattern");
		cityPat = Pattern.compile(cityPattern);
		String zipPattern = getInitParameter("zipPattern");
		zipPat = Pattern.compile(zipPattern);
		String ccnPattern = getInitParameter("ccnPattern");
		ccnPat = Pattern.compile(ccnPattern);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		String userName = request.getParameter("userName");
		if (userName == null || userName.length() < 6 || userName.length() > 20){
			genBadIdPage("Please verify input", request,
					response);
			return;
		}

		Matcher patternMatcher = userNamePat.matcher(userName);

		if (patternMatcher.find()) {
			genBadIdPage("Please verify input", request, response);
			return;
		}

		String name = request.getParameter("fullName");
		if (name != null && name.length() > 0) {
			if (name.length() > 40) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
			patternMatcher = namePat.matcher(name);

			if (patternMatcher.find()) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
		}

		String street = request.getParameter("street");
		if (street != null && street.length() > 0) {
			if (street.length() > 30) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
			patternMatcher = streetPat.matcher(street);

			if (patternMatcher.find()) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
		}

		String city = request.getParameter("city");
		if (city != null && city.length() > 0) {
			if (city.length() > 30) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
			patternMatcher = cityPat.matcher(city);

			if (patternMatcher.find()) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
		}

		String zip = request.getParameter("zip");
		if (zip != null && zip.length() > 0) {
			if (zip.length() < 5 || zip.length() > 10) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
			patternMatcher = zipPat.matcher(zip);

			if (!patternMatcher.find()) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
		}

		String ccn = request.getParameter("ccn");
		if (ccn != null && ccn.length() > 0) {
			if (ccn.length() < 16 || ccn.length() > 19) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
			patternMatcher = ccnPat.matcher(ccn);

			if (!patternMatcher.find()) {
				genBadIdPage("Please verify input", request, response);
				return;
			}
		}

		// Setup the empty bean
		User newUser = new User();

		// Populate the bean with the request parameters
		newUser.setUserName(userName);
		newUser.setFullName(name);
		newUser.setStreet(street);
		newUser.setCity(city);
		newUser.setZipcode(zip);
		newUser.setCcn(ccn);

		String username = newUser.getUserName();

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			response.sendRedirect("index.html");
			return;
		}
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "UPDATE MEMBER SET FULLNAME = '" + newUser.getFullName()
					+ "', STREET = '" + newUser.getStreet() + "', CITY = '"
					+ newUser.getCity() + "', ZIPCODE = '" + newUser.getZipcode()
					+ "', CCN = '" + newUser.getCcn() + "' WHERE USERNAME = '" + username
					+ "'";
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

		// Add the bean to the request
		request.setAttribute("user", newUser);
		request.getRequestDispatcher("JSPs/UserUpdateSuccess.jsp").forward(request,
				response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
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
