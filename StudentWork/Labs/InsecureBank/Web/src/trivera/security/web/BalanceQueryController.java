package trivera.security.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trivera.security.data.Account;
import trivera.security.data.Bank;
import trivera.security.data.BankInfoStore;

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

public class BalanceQueryController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		String accountID = (String) request.getParameter("acct");
		// If ID, no more
		if (accountID == null || accountID.isEmpty()) {
			genBadIdPage("Please enter a valid account number.",
					request, response);
			return;
		}	
		
		BankInfoStore store = Bank.store;
		
		Account target = store.getAccount(accountID);
		
		// If no match, no more
		if (target == null) {
			genBadIdPage(accountID + " is not a valid account number.",
					request, response);
			return;
		}	
		
		
		request.setAttribute("results", target);
		request.getRequestDispatcher("JSPs/BalanceResults.jsp").forward(request,
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
