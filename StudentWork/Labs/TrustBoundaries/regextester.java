import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class regextester {

	public static void main(String[] args) {
		String sampleText = "s sss";
		String sampleRegex = "[^A-Za-z0-9._\s-]+";
		Pattern p = Pattern
				.compile(sampleRegex);
		Matcher m = p.matcher(sampleText);
		if (m.find()) {
			String matchedText = m.group();
			int matchedFrom = m.start();
			int matchedTo = m.end();
			System.out.println("matched [" + matchedText + "] from "
					+ matchedFrom + " to " + matchedTo + ".");
		} else {
			System.out.println("didn’t match");
		}
	}
}
