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
import trivera.security.data.ClientInfo;

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

public class TransferController extends HttpServlet {
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
	
		String candidateCsrfValue = request.getParameter("csrfPrevention");
		Long csrfBase = (Long)thisSession.getAttribute("csrfValue");
		
		if (candidateCsrfValue == null || !candidateCsrfValue.equals(csrfBase.toString())) {
			thisSession.invalidate();
			return;
		};
		
		String source = (String) request.getParameter("source");
		// If no source, no more
		if (source == null || source.isEmpty()) {
			genBadIdPage("Please enter a valid account type for you to transfer from.",
					request, response);
			return;
		};			
		
		String target = (String) request.getParameter("target");
		// If no target, no more
		if (target == null || target.isEmpty()) {
			genBadIdPage("Please enter a valid account type for you to transfer to.",
					request, response);
			return;
		};		
		
		String amount = (String) request.getParameter("amount");
		// If no amount, no more
		if (amount == null || amount.isEmpty()) {
			genBadIdPage("Please enter a valid amount to transfer.",
					request, response);
			return;
		}		

		BankInfoStore store = Bank.store;
		ClientInfo authenticatedUser = (ClientInfo) thisSession.getAttribute("client");
		
		Account sourceAccount = store.getAccount(source, authenticatedUser.getUsername());
		
		// If no match, no more
		if (sourceAccount == null) {
			genBadIdPage(source + " is not a valid account type for you to transfer from.",
					request, response);
			return;
		}	
		
		Account targetAccount = store.getAccount(target, authenticatedUser.getUsername());
		
		// If no match, no more
		if (targetAccount == null) {
			genBadIdPage(target + " is not a valid account type for you to transfer to.",
					request, response);
			return;
		}
		
		Integer amt;
		
		try {
			amt = Integer.valueOf(amount);
		} catch (NumberFormatException e) {
			genBadIdPage("Not a valid amount.",
					request, response);
			return;
		}

		Integer oldBalance = sourceAccount.getBalance();
		
		if (amt > oldBalance) {
			genBadIdPage("Insufficent funds to cover transfer.",
					request, response);
			return;
		};

		Integer newBalance = oldBalance - amt;
		
		sourceAccount.setBalance(newBalance);
		
		oldBalance = targetAccount.getBalance();
		
		newBalance = oldBalance + amt;
		
		targetAccount.setBalance(newBalance);
		
		request.setAttribute("targetacct", targetAccount);
		request.setAttribute("sourceacct", sourceAccount);
		request.setAttribute("amt", amount);
		request.getRequestDispatcher("JSPs/TransferResults.jsp").forward(
				request, response);
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
