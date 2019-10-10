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

package trivera.security.auth;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import trivera.security.data.ConnectionFactory;

public class AgentRegistry {
	/** ***************************************************************** */
	/** ***********************Variables********************************* */
	/** ***************************************************************** */

	/** ***************************************************************** */
	/** ***********************Constructor******************************* */
	/** ***************************************************************** */

	/**
	 * Constructor initializes registry.
	 */
	public AgentRegistry() {
		super();

	}

	/** ***************************************************************** */
	/** ****************Methods****************************** */
	/** ***************************************************************** */

	public int checkAgent(String candidateName, String candidatePassword) {
		if (candidateName == null || candidatePassword == null)
			return 0;

		// **********************************************************************
		// Uses the BouncyCastle Security Provider that can be found at
		// www.bouncycastle.org
		// JAR file must be in build and run path (i.e. bcprov-jdk15-133.jar)
		Security.addProvider(new BouncyCastleProvider());

		// Advantagous to make as long as feasable increases complexity of generated
		// has
		char[] passwordArray = candidatePassword.toCharArray();
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

			candidatePassword = toHex(sKey.getEncoded());

			System.out.println("generated hash: " + toHex(sKey.getEncoded()));
		} catch (NoSuchAlgorithmException e1) {
				System.out.println("Hash operation failed");
				return 0;
		} catch (InvalidKeySpecException e) {
			System.out.println("Hash operation failed");
			return 0;
		}

		// **********************************************************************
		
		
		Connection conn = ConnectionFactory.getConnection();
		if (conn == null)
			return 0;
		ResultSet rs = null;
		String password = null;

		Statement sqlStatement = null;
		try {
			sqlStatement = conn.createStatement();
			String sql = "select PASSWORD from MEMBER WHERE USERNAME = '"
					+ candidateName + "'";
			System.out.println(sql);
			rs = sqlStatement.executeQuery(sql);
			while (rs.next()) {
				password = rs.getString(1);
				System.out.println("Password:" + password);
				System.out.println("Candidate Password:" + candidatePassword);
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			Statement[] statements = new Statement[1];
			statements[0] = sqlStatement;
			ConnectionFactory.closeConnection(conn, statements);
		}

		if (password == null)
			return 0;
		if (candidatePassword.equals(password))
			return 2;
		else
			return 1;
	}
	
	/** ***************************************************************** */
	/** ***********************Utilities********************************* */
	/** ***************************************************************** */

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
