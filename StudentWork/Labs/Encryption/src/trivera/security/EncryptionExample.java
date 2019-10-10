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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * Basic symmetric encryption example
 */
public class EncryptionExample
{   
    public static void main(
        String[]    args)
        throws Exception
    {
        String inputString = "testPassword";
    	byte[]        input = toByteArray(inputString);
        
    	//Statements below essentially create the key.  
    	//Key or KeyBytes could be persisted and protected out of band
        byte[]        keyBytes = new byte[] { 0x00, 0x01, 0x02,
                0x03, 0x04, 0x05, 0x06, 0x07 };
        
        // create a 64 bit secret key from raw bytes
        SecretKey key64 = new SecretKeySpec(keyBytes, "Blowfish");

        // create a cipher and attempt to encrypt the data block with the key

        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key64);
        
        // encryption pass
        
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        
        ctLength += cipher.doFinal(cipherText, ctLength);
        

        System.out.println("input text : " + inputString);
        System.out.println("cipher text: " + toHex(cipherText));
        
        
        // decryption pass
        
        byte[] plainText = new byte[cipher.getOutputSize(cipherText.length)];
        
        cipher.init(Cipher.DECRYPT_MODE, key64);

        int ptLength = cipher.update(cipherText, 0, ctLength, plainText, 0);
        
        ptLength += cipher.doFinal(plainText, ptLength);
        
        System.out.println("plain text : " + toConvertedString(plainText, ptLength));

        
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

    /**
     * Convert the passed in String to a byte array by
     * taking the bottom 8 bits of each character it contains.
     * 
     * @param string the string to be converted
     * @return a byte array representation
     */
    public static byte[] toByteArray(
        String string)
    {
        byte[]	bytes = new byte[string.length()];
        char[]  chars = string.toCharArray();
        
        for (int i = 0; i != chars.length; i++)
        {
            bytes[i] = (byte)chars[i];
        }
        
        return bytes;
    }
    
    /**
     * Convert a byte array of 8 bit characters into a String.
     * 
     * @param bytes the array containing the characters
     * @param length the number of bytes to process
     * @return a String representation of bytes
     */
    public static String toConvertedString(
        byte[] bytes,
        int    length)
    {
        char[]	chars = new char[length];
        
        for (int i = 0; i != chars.length; i++)
        {
            chars[i] = (char)(bytes[i] & 0xff);
        }
        
        return new String(chars);
    }

}
