package org.filho.util.blowfish.test;

import java.util.Date;

import org.filho.util.blowfish.hasher.BlowfishHasher;
import org.filho.util.blowfish.shared.BlowfishSecurity;
import org.junit.Test;

public class BlowfishEncryptionTest {
	
	
	private static final String key = "abc123";
	private static final String data = "this.is.a.dot.test";
	private static final Date date = new Date(1451613600000L); // 01/01/2016
	
	@Test
	public void testEncryption() {
		BlowfishSecurity hasher = new BlowfishHasher();
		
		String encrypted = hasher.encrypt(key, date, data);
		
		String decrypted = hasher.decrypt(key, encrypted);
		
		System.out.println(date);
		System.out.println(encrypted);
		System.out.println(decrypted);
	}
	
}	