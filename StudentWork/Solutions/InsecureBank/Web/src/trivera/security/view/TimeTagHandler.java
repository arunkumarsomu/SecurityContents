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

package trivera.security.view;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TimeTagHandler extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TimeTagHandler() {
		super();
	}

	public int doStartTag() throws JspException {
		try {
			final JspWriter out = pageContext.getOut();
			SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date c = new Date();
			out.print(s.format(c));
			return EVAL_PAGE;
		} catch (IOException e) {
			throw new JspException("Error writing start tag.");
		}
	}
}
