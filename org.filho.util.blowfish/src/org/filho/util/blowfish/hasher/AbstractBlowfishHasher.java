package org.filho.util.blowfish.hasher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Generates hashes from messages given a specific key.
 * @author Roberto Filho
 *
 */
public class AbstractBlowfishHasher {

	private String key;
	private String encoding = "UTF-8";
	
	public AbstractBlowfishHasher(String key) {
		super();
		this.key = key;
	}

	public String encodeHash(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// Transforms the key into bytes
		byte[] keyData = key.getBytes(encoding);
		
		SecretKeySpec KS = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, KS);
		
	    // encrypt message
	    byte[] encrypted = cipher.doFinal(message.getBytes(encoding));
	    
	    return convertStringToHex(new String(encrypted, encoding));
	}
	
	public String decodeHash(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		// Transforms the key into bytes
		byte[] keyData = key.getBytes(encoding);
		
		SecretKeySpec secretKey = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
	    // encrypt message
	    byte[] encrypted = cipher.doFinal(message.getBytes(encoding));
	    return convertHexToString(new String(encrypted, encoding));
	}
	
	public String convertStringToHex(String str) {
		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public String convertHexToString(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}
}
