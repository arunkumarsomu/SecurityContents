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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trivera.security.data.policy.Policy;
import trivera.security.data.policy.PolicyStore;

/**
 * This servlet controls processing associated with policy-related user
 * interactions.
 * 
 * @version Training Course
 */
public class PolicyController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** ***************************************************************** */
	/** ***********************Variables********************************* */
	/** ***************************************************************** */
	// Policy Store
	PolicyStore thisStore = null;

	ServletConfig configuration = null;

	/** ***************************************************************** */
	/** ***********************Servlet Methods*************************** */
	/** ***************************************************************** */
	/**
	 * init() method policy data store.
	 */
	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);
		configuration = config;

		// Instantiate a PolicyStore class
		thisStore = new PolicyStore(getServletContext());
	}

	/**
	 * Process incoming HTTP POST request doPost() method responds to the request
	 * 
	 * @param request
	 *          Object that encapsulates the request to the servlet
	 * @param response
	 *          Object that encapsulates the response from the servlet
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check to ensure that user has logged in
		HttpSession thisSession = request.getSession(false);
		if (thisSession == null || !request.isRequestedSessionIdValid()) {
			response.sendRedirect("login.html");
			return;
		}

		Boolean loginTest = (Boolean) thisSession.getAttribute("loggedIn");
		if (loginTest == null || !loginTest.booleanValue()) {
			response.sendRedirect("login.html");
			return;
		}

		// Check to make sure that we have a policy store to query against.
		if (thisStore == null) {
			genBadIdPage("There are currently no policies available.", request,
					response);
			return;
		}

		// See if the user put anything into the text string
		String policyNumber = request.getParameter("polNum");

		// Check for match.
		Policy target = thisStore.searchStore(policyNumber);

		// If no match, no more
		if (target == null) {
			genBadIdPage(policyNumber + " is not a valid policy number.", request,
					response);
			return;
		}

		String username = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
					break;
				}
			}
		}

		// Set up the response for html output
		response.setContentType("text/html");
		configuration.getServletContext().log(
						"Policy Number " + policyNumber + " for " + username
								+ " was retrieved");

		// Statements to forward to JSP viewer
		request.setAttribute("policyBean", target);
		request.getRequestDispatcher("JSPs/PolicyDisplay.jsp").forward(request,
				response);
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
