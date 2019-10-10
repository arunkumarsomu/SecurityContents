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

package trivera.security.web;

import java.io.IOException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trivera.security.auth.AgentRegistry;

/**
 * This servlet controls processing associated with performing a login and
 * setting up the associated login session.
 *
 * @version Training Course
 */
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** ***************************************************************** */
	/** ***********************Variables********************************* */
	/** ***************************************************************** */
	// Agent Registry
	public AgentRegistry thisReg;
	private Pattern userNamePat = null;
	private Pattern passwordPat = null;
	private Hashtable<String, Integer> userStash = new Hashtable<String, Integer> ();

	/** ***************************************************************** */
	/** ***********************Servlet Methods*************************** */
	/** ***************************************************************** */

	/**
	 * init() method sets up AgentRegistry.
	 */
	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);
		String userNamePattern = getInitParameter("userNamePattern");
		userNamePat = Pattern.compile(userNamePattern);
		String passwordPattern = getInitParameter("passwordPattern");
		passwordPat = Pattern.compile(passwordPattern);

		// Instantiate an AgentRegistry class
		thisReg = new AgentRegistry();
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user = (String) req.getParameter("user");
		if (user == null || user.length() < 6 || user.length() > 20){
			genBadIdPage("Invalid login", req,
					resp);
			return;
		}

		Matcher patternMatcher = userNamePat.matcher(user);

		if (patternMatcher.find()) {
			genBadIdPage("Invalid login", req, resp);
			return;
		}

		if (checkUserCount(user)){
			genBadIdPage("Account disabled", req, resp);
			return;
		}

		String password = (String) req.getParameter("password");
		if (password == null || password.length() < 6 || password.length() > 20){
			incrementUser(user);
			genBadIdPage("Invalid login", req,
					resp);
			return;
		}

		patternMatcher = passwordPat.matcher(password);

		if (patternMatcher.find()) {
			incrementUser(user);
			genBadIdPage("Invalid login", req, resp);
			return;
		}

		int result = thisReg.checkAgent(user, password);

		if (result == 0 || result == 1) {
			incrementUser(user);
			genBadIdPage("Invalid login", req, resp);
			return;
		}

		clearUser(user);

		HttpSession logSession = req.getSession(true);
		logSession.setMaxInactiveInterval(30000);
		logSession.setAttribute("loggedIn", new Boolean("true"));
		Cookie cookie = new Cookie("username", user);
		resp.addCookie(cookie);
		resp.sendRedirect("index.html");
	}

	/** ***************************************************************** */
	/** ***********************Utilities********************************* */
	/** ***************************************************************** */

	private void genBadIdPage(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("ErrorHandler").forward(request, response);
	}

	private void incrementUser(String user) {
		Integer value;
		Object candidate = userStash.get(user);
		if (candidate == null) {
			value = new Integer(1);
		} else {
			value = (Integer)candidate;
			value = value.intValue() + 1;
		}
		userStash.put(user, value);
	}

	private void clearUser(String user) {
		userStash.remove(user);
	}

	private boolean checkUserCount(String user) {
		Object candidate = userStash.get(user);
		if (candidate == null) {
			return false;
		} else {
			int test = ((Integer)candidate).intValue();
			if (test >= 4)
				return true;
			else
				return false;
		}
	}

}
