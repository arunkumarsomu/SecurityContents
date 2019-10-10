package trivera.security;

/**
 * <p>
 * This component and its source code representation are copyright protected
 * and proprietary to Trivera Technologies, LLC, Worldwide D/B/A Trivera Technologies
 *
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of Trivera Technologies, LLC
 *
 * Copyright (c) 2019 Trivera Technologies, LLC.
 * http://www.triveratech.com   
 * </p>
 * @author Trivera Technologies Tech Team.
 */

import java.security.Key;
import java.security.Security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Example of generating a hash using a combination of a string, a salt,
 * and an interation count.  Once reviewed, run and examine as a Java application
 * to understand impacts of changing values and so on...
 */
public class HashingExample
{
    public static void main(
        String[]    args)
        throws Exception
    {
    	//Uses the BouncyCastle Security Provider that can be found at www.bouncycastle.org
    	//JAR file must be in build and run path (i.e. bcprov-jdk15-133.jar)
        Security.addProvider(new BouncyCastleProvider());
        
        
        //Advantagous to make as long as feasable increases complexity of generated has
        char[]              password = "testpassword".toCharArray();
        //Salt can be stored in variety of locations to provide decoupling and layer of defense
        //Attackers must know, then must perform longer and more complex hash generations
        byte[]              saltValue = new byte[] { 0x7e, 0x70, 0x22, 0x5e, 0x72, (byte)0xe9, (byte)0xe0, (byte)0xae };
        //Adds another layer of defense, at a minimum this causes an attacker to consume
        //more resources to generate candidate passwords.  The higher the number, the 
        //more costly it is for the attacker even if the interation value is known. 
        //Want to make as large as it feasible.
        int                 iterations = 2048;
        PBEKeySpec          pbeSpec = new PBEKeySpec(password, saltValue, iterations);
        SecretKeyFactory    keyFact = SecretKeyFactory.getInstance("PBEWithSHAAnd3KeyTripleDES");

        Key    sKey = keyFact.generateSecret(pbeSpec);

     
        System.out.println("generated hash: " + toHex(sKey.getEncoded()));
    }
    
    /**
     * Return the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
    	String	digits = "0123456789abcdef";
    	int length = data.length;
    	StringBuffer	buf = new StringBuffer();
        
        for (int i = 0; i != length; i++)
        {
            int	v = data[i] & 0xff;
            
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        
        return buf.toString();
    }

}
