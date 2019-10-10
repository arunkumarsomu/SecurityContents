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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trivera.security.auth.AgentRegistry;
import trivera.security.data.Bank;
import trivera.security.data.BankInfoStore;
import trivera.security.data.ClientInfo;

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
	
	//Random Number Generator
	Random numberGen;

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
		
		//Create a random number generator
		try {
			numberGen = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			numberGen = new Random();
		}
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
		
		BankInfoStore store = Bank.store;
		ClientInfo client = store.getClient(user);
		logSession.setAttribute("client", client);
		
		Cookie cookie = new Cookie("username", user);
		resp.addCookie(cookie);
		
		Long newRandom = numberGen.nextLong();
		
		logSession.setAttribute("csrfValue", newRandom);
	
		resp.sendRedirect("accountops.html");
	}
}
