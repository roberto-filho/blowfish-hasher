package org.filho.util.blowfish;
/*
 * Based on https://raw.githubusercontent.com/usefulfor/usefulfor/master/security/JBoss.java
 *
 * JBoss.java - Blowfish encryption/decryption tool with JBoss default password
 *    Daniel Martin Gomez <daniel@ngssoftware.com> - 03/Sep/2009
 *
 * This file may be used under the terms of the GNU General Public License 
 * version 2.0 as published by the Free Software Foundation:
 *   http://www.gnu.org/licenses/gpl-2.0.html
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class JBlowfish 
{
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private final static String key = "secret123"; 

	// Converts byte array to hex string
	// From: http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(doThis("e", key, "testdata$20160101"));
		System.out.println(doThis("d", key, "1730A04E2D9D4069EA59978373BCA3148158CF2147E5B10E"));;
	}
	
	public static String doThis(String mode, String ekey, String message) throws Exception {
		// Configuration
		byte[] key	= ekey.getBytes();
		String IV  	= "12345678";
		
		// Create new Blowfish cipher
		SecretKeySpec keySpec = new SecretKeySpec(key, "Blowfish"); 
		Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding"); 
		String out = null;

		if (mode.equals("e")) {
			String secret = message;
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(IV.getBytes()));
			byte[] encoding = cipher.doFinal(secret.getBytes());

			return bytesToHex(encoding);
		} else {
			// Decode Base64
			byte[] ciphertext = DatatypeConverter.parseHexBinary(message);

			// Decrypt
			cipher.init(Cipher.DECRYPT_MODE, keySpec, new javax.crypto.spec.IvParameterSpec(IV.getBytes()));
			byte[] decrypted = cipher.doFinal(ciphertext);

			return new String(decrypted);
		}
	}
}	