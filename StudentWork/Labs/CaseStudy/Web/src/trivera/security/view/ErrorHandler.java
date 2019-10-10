package trivera.security.view;


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

import java.io.*;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class generally handles errors for the training labs. This assumes that
 * appropriate values have been set in the header prior to forwarding to this.
 * Also, checks for a parameter on the request called "message" for any error
 * message that might be provided.
 */
public class ErrorHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processError(req, resp);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processError(req, resp);
	}

	private void processError(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PrintWriter out = resp.getWriter();
		String message = (String) req.getAttribute("message");

		out.println("<html>");
		out.println("<head></head>");
		out.println("<body bgcolor=\"#E6E6FF\">\n");
		out.println("<h1 align=center>RHH Insurance</h1>");
		out.println("<h2 align=center>Unable to Respond</h2>");
		out.println("<h3 align=center><font color=#FF000>");

		if (message != null && message.length() > 0)
			out.println(message + "</font></h3>");
		else
			out.println("No error message provided.</font></h3>");
		out.println("<hr/><a href='index.html' >Home</a><br/><hr/>");

		out.println("</body></html>");
		out.close();
	}
}
