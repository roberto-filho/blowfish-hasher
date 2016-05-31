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

	public String generateHash(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// Transforms the key into bytes
		byte[] keyData = key.getBytes(encoding);
		
		SecretKeySpec KS = new SecretKeySpec(keyData, "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, KS);
		
	    // encrypt message
	    byte[] encrypted = cipher.doFinal(message.getBytes(encoding));
	    
	    return new String(encrypted);
		
		/*
		KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");

	    keyGenerator.init(128);
	    Key secretKey = keyGenerator.generateKey();

	    Cipher cipherOut = Cipher.getInstance("Blowfish/CFB/NoPadding");

	    cipherOut.init(Cipher.ENCRYPT_MODE, secretKey);
	    BASE64Encoder encoder = new BASE64Encoder();
	    byte iv[] = cipherOut.getIV();
	    
	    if (iv != null) {
	      System.out.println("Initialization Vector of the Cipher:\n" + encoder.encode(iv));
	    }
	    
	    
	    FileInputStream fin = new FileInputStream("inputFile.txt");
	    FileOutputStream fout = new FileOutputStream("outputFile.txt");
	    CipherOutputStream cout = new CipherOutputStream(fout, cipherOut);
	    int input = 0;
	    while ((input = fin.read()) != -1) {
	      cout.write(input);
	    }

	    fin.close();
	    cout.close();
	    */
	}
}
