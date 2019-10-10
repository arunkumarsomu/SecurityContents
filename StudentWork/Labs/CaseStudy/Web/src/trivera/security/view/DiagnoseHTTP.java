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

package trivera.security.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This servlet extracts out all information from the HTTP request and returns
 * it in an HTML page.
 * 
 * @version Training Course
 */

public class DiagnoseHTTP extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head><title>HTTP Diagnosis</title></head>");
		out.println("<body  bgcolor=#E6E6FF>");
		out.println("<h1 align=center>RHH Insurance HTTP Diagnostics</h1><hr>");
		out.println("<h2>Requested URL:</h2>");
		out.println("<pre>");
		out.println(request.getRequestURL().toString());
		out.println("</pre><hr>");

		out.println("<h2>Servlet Initialization Parameters</h2>");
		out.println("<pre>");
		out.println("Servlet init parameters:");
		Enumeration e = getInitParameterNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = getInitParameter(key);
			out.println("   " + key + " = " + value);
		}
		out.println();

		out.println("Context init parameters:");
		ServletContext context = getServletContext();
		Enumeration params = context.getInitParameterNames();
		while (params.hasMoreElements()) {
			String key = (String) params.nextElement();
			Object value = context.getInitParameter(key);
			out.println("   " + key + " = " + value);
		}
		out.println("</pre><hr>");
		out.println("<h2>Request information:</h2>");
		out.println("<pre>");
		out.println("<b> Servlet Name: </b>" + getServletName());
		out.println("<b> Protocol: </b>" + request.getProtocol());
		out.println("<b> Scheme: </b>" + request.getScheme());
		out.println("<b> Server Name: </b>" + request.getServerName());
		out.println("<b> Server Port: </b>" + request.getServerPort());
		out.println("<b> Server Info: </b>" + context.getServerInfo());
		out.println("<b> Remote Addr: </b>" + request.getRemoteAddr());
		out.println("<b> Remote Host: </b>" + request.getRemoteHost());
		out
				.println("<b> Character Encoding: </b>"
						+ request.getCharacterEncoding());
		out.println("<b> Content Length: </b>" + request.getContentLength());
		out.println("<b> Content Type: </b>" + request.getContentType());
		out.println("<b> Locale: </b>" + request.getLocale());
		out.println("<b> Default Response Buffer: </b>" + response.getBufferSize());
		out.println("</pre><hr>");

		e = request.getParameterNames();
		if (e.hasMoreElements()) {
			out.println("<h2>Parameters in this request:</h2>");
			out.println("<pre>");
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String vals[] = (String[]) request.getParameterValues(name);
				if (vals != null) {
					out.print("<b> " + name + "</b> = ");
					out.println(vals[0]);
					for (int i = 1; i < vals.length; i++)
						out.println("           " + vals[i]);
				}
				out.println("<p>");
			}
			out.println("</pre><hr>");
		} else
			out.println("<h2>No parameters in this request.</h2><hr>");

		out.println("<h2>Header information in this request:</h2>");
		out.println("<pre>");
		e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = request.getHeader(key);
			out.println("<b>" + key + ":</b> " + value);
		}
		out.println("</pre><hr>");

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			out.println("<h2>Cookies in this request:</h2>");
			out.println("<pre>");
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				out.println("<b>" + cookie.getName() + "</b> = " + cookie.getValue());
			}
			out.println("</pre><hr>");
		} else
			out.println("<h2>No cookies in this request.</h2><hr>");

		out.println("<h2>Additional request information:</h2>");
		out.println("<pre>");
		out.println("<b>Request Is Secure: </b>" + request.isSecure());
		out.println("<b>Auth Type: </b>" + request.getAuthType());
		out.println("<b>HTTP Method: </b>" + request.getMethod());
		out.println("<b>Remote User: </b>" + request.getRemoteUser());
		out.println("<b>Request URI: </b>" + request.getRequestURI());
		out.println("<b>Context Path: </b>" + request.getContextPath());
		out.println("<b>Servlet Path: </b>" + request.getServletPath());
		out.println("<b>Path Info: </b>" + request.getPathInfo());
		out.println("<b>Path Trans: </b>" + request.getPathTranslated());
		out.println("<b>Query String: </b>" + request.getQueryString());
		out.println("</pre><hr>");

		out.println("<h2>Session Related Information:</h2>");
		out.println("<pre>");
		out.println("<b>Session ID from Request: </b>"
				+ request.getRequestedSessionId());
		HttpSession session = request.getSession();
		if (session.isNew()) {
			out
					.println("<b><i>Note that requested session has expired. New session created.</b></i>");
		}
		;
		out.println("<b>Current Session Id: </b>" + session.getId());
		DateFormat dater = DateFormat.getDateTimeInstance();
		out.println("<b>Session Created Time: </b>"
				+ dater.format(new Date(session.getCreationTime())));
		if (session.isNew())
			out.println("<b>New session - not previously accessed.</b>");
		else
			out.println("<b>Session Last Accessed Time: </b>"
					+ dater.format(new Date(session.getLastAccessedTime())));
		out.println("<b>Session Max Inactive Interval Seconds: </b>"
				+ session.getMaxInactiveInterval());

		Enumeration names = session.getAttributeNames();
		if (!names.hasMoreElements())
			out
					.println("<b>No session values are stored on server at this time.</b>");
		else {
			out.println("<b>Session values (stored on server): </b>");
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				out.println("<b>" + name + "</b> = " + session.getAttribute(name));
			}
		}
		out.println("</pre><hr>");
	}
}
