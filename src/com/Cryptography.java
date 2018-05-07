package com;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {
	
	private static final String SUPER_SECRET_KEY = "41CF9345111A23650E56C883140A70A8";

	public static String encrypt(String strClearText,String strKey) throws Exception{
	    String strData="";

	    try {
      
	        Key aesKey = new SecretKeySpec(strKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData = new String(encrypted);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception(e);
	    }
	    return strData;
	}
	
	public static String decrypt(String strEncrypted,String strKey) throws Exception{
	    String strData="";

	    try {
	       
	        Key aesKey = new SecretKeySpec(strKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decrypted  = cipher.doFinal(strEncrypted.getBytes());
            strData = new String(decrypted);

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception(e);
	    }
	    return strData;
	}
	
	public static String encrypt(String str) {
		try {
			return encrypt(str, SUPER_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decrypt(String str) {
		try {
			return decrypt(str, SUPER_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
