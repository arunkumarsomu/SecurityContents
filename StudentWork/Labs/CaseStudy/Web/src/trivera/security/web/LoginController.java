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

package trivera.security.web;

import java.io.IOException;
import javax.servlet.*;

import javax.servlet.http.*;

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

	/** ***************************************************************** */
	/** ***********************Servlet Methods*************************** */
	/** ***************************************************************** */

	/**
	 * init() method sets up AgentRegistry.
	 */
	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);

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
		String password = (String) req.getParameter("password");

		int result = thisReg.checkAgent(user, password);

		if (result == 0) {
			resp.sendRedirect("relogin.html");
			return;
		}

		if (result == 1) {
			resp.sendRedirect("reloginpw.html");
			return;
		}

		HttpSession logSession = req.getSession(true);
		logSession.setMaxInactiveInterval(30000);
		logSession.setAttribute("loggedIn", new Boolean("true"));
		Cookie cookie = new Cookie("username", user);
		resp.addCookie(cookie);
		resp.sendRedirect("index.html");
	}
}
