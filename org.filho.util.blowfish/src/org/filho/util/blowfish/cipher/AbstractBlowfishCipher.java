package org.filho.util.blowfish.cipher;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.filho.util.blowfish.hasher.Mode;

/**
 * Contains all the methods to manipulate the blowfish hashes.
 * @author Roberto Filho
 *
 */
public abstract class AbstractBlowfishCipher implements BlowfishCipher {
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private Mode mode;

	public AbstractBlowfishCipher(Mode mode) {
		this.mode = mode;
	}

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
	
	public Cipher getCipher(Mode mode, SecretKeySpec keySpec) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		// Use blowfish with ECB and PKCS5 padding
		Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding"); 

		// Set mode
		cipher.init(mode.getCipherMode(), keySpec);
		return cipher;
	}
	
	public SecretKeySpec getKey(String key) {
		// Configuration
		byte[] keyBytes	= key.getBytes();
		
		// Create new Blowfish cipher
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "Blowfish");
		return keySpec;
	}
	
	protected Mode getMode() {
		return mode;
	}
}
