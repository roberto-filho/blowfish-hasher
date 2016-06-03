package org.filho.util.blowfish;

import org.filho.util.blowfish.cipher.api.BlowfishCipher;
import org.filho.util.blowfish.hasher.BlowfishBuilder;
import org.filho.util.blowfish.hasher.Mode;

public class BlowfishEncryption {
	
	
	private static final String key = "abc123";
	private static final String data = "this.is.a.dot.test";
	
	public static void main(String[] args) throws Exception {
		BlowfishCipher cipher = BlowfishBuilder.get(Mode.ENCRYPT).useHex().build();
		
		String encrypted = cipher.doAction(key, data);
		
		System.out.println(encrypted);
		//System.out.println(doThis("d", key, "1730A04E2D9D4069EA59978373BCA3148158CF2147E5B10E", true));
		//System.out.println(doThis("d", key, "2WwJV5q1MvN4zTgO3dGLObogAPPOyxfR"));
		
		cipher = BlowfishBuilder.get(Mode.DECRYPT).useHex().build();
		
		System.out.println(cipher.doAction(key, encrypted));
	}
	
}	