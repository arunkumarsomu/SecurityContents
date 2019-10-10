package trivera.security.web;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import trivera.security.data.ConnectionFactory;

/**
 * <p>
 * This component and its source code representation are copyright protected and
 * proprietary to Kim Morello, Worldwide
 *
 * This component and source code may be used for instructional and evaluation
 * purposes only. No part of this component or its source code may be sold,
 * transferred, or publicly posted, nor may it be used in a commercial or
 * production environment, without the express written consent of the Trivera
 * Group, Inc.
 *
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * </p>
 *
 * @author Kim Morello.
 */

public class PwdUpdateController extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Pattern userNamePat = null;

	private Pattern passwordPat = null;

	public void init(ServletConfig config) throws ServletException {
		// Always call super.init
		super.init(config);
		String userNamePattern = getInitParameter("userNamePattern");
		userNamePat = Pattern.compile(userNamePattern);
		String passwordPattern = getInitParameter("passwordPattern");
		passwordPat = Pattern.compile(passwordPattern);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String) req.getParameter("username");
		if (user == null || user.length() < 6 || user.length() > 20) {
			genBadIdPage("Not a legal username", req, resp);
			return;
		}

		Matcher patternMatcher = userNamePat.matcher(user);

		if (patternMatcher.find()) {
			genBadIdPage("Not a legal username", req, resp);
			return;
		}

		String password = (String) req.getParameter("password");

		if (password == null || password.length() < 6 || password.length() > 20) {
			genBadIdPage("Not a legal password", req, resp);
			return;
		}

		patternMatcher = passwordPat.matcher(password);

		if (patternMatcher.find()) {
			genBadIdPage("Not a legal password", req, resp);
			return;
		}

		// **********************************************************************
		// Uses the BouncyCastle Security Provider that can be found at
		// www.bouncycastle.org
		// JAR file must be in build and run path (i.e. bcprov-jdk15-133.jar)
		Security.addProvider(new BouncyCastleProvider());

		// Advantagous to make as long as feasable increases complexity of generated
		// has
		char[] passwordArray = password.toCharArray();
		// Salt can be stored in variety of locations to provide decoupling and
		// layer of defense
		// Attackers must know, then must perform longer and more complex hash
		// generations
		byte[] saltValue = new byte[] { 0x7e, 0x70, 0x22, 0x5e, 0x72, (byte) 0xe9,
				(byte) 0xe0, (byte) 0xae };
		// Adds another layer of defense, at a minimum this causes an attacker to
		// consume
		// more resources to generate candidate passwords. The higher the number,
		// the
		// more costly it is for the attacker even if the interation value is known.
		// Want to make as large as it feasible.
		int iterations = 2048;
		PBEKeySpec pbeSpec = new PBEKeySpec(passwordArray, saltValue, iterations);
		SecretKeyFactory keyFact;
		try {
			keyFact = SecretKeyFactory.getInstance("PBEWithSHAAnd3KeyTripleDES");

			Key sKey = keyFact.generateSecret(pbeSpec);

			password = toHex(sKey.getEncoded());

			System.out.println("generated hash: " + toHex(sKey.getEncoded()));
		} catch (NoSuchAlgorithmException e1) {
				genBadIdPage("Hash operation failed", req, resp);
				return;
		} catch (InvalidKeySpecException e) {
			genBadIdPage("Hash operation failed", req, resp);
			return;
		}

		// **********************************************************************

		Connection conn = ConnectionFactory.getConnection();
		if (conn == null) {
			resp.sendRedirect("index.html");
			return;
		}
		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "UPDATE MEMBER SET PASSWORD = '" + password
					+ "' WHERE USERNAME = '" + user + "'";
			System.out.println(sql);
			sqlStatement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("index.html");
			return;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}

		req.setAttribute("username", user);
		req.getRequestDispatcher("JSPs/PwdUpdateSuccess.jsp").forward(req, resp);
	}

	/** ***************************************************************** */
	/** ***********************Utilities********************************* */
	/** ***************************************************************** */

	private void genBadIdPage(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("ErrorHandler").forward(request, response);
	}

	/**
	 * Return the passed in byte array as a hex string.
	 *
	 * @param data
	 *          the bytes to be converted.
	 * @return a hex representation of data.
	 */
	public static String toHex(byte[] data) {
		String digits = "0123456789abcdef";
		int length = data.length;
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i != length; i++) {
			int v = data[i] & 0xff;

			buf.append(digits.charAt(v >> 4));
			buf.append(digits.charAt(v & 0xf));
		}

		return buf.toString();
	}

}
