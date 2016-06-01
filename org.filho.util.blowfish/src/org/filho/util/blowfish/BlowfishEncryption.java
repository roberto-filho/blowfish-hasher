package org.filho.util.blowfish;

import org.filho.util.blowfish.hasher.Mode;

public class BlowfishEncryption {
	
	
	private final static String key = "secret";
	
	public static void main(String[] args) throws Exception {
		String encrypted = Mode.ENCRYPT.generateResult(key, "testdata$20160101", false);
		System.out.println(encrypted);
		//System.out.println(doThis("d", key, "1730A04E2D9D4069EA59978373BCA3148158CF2147E5B10E", true));
		//System.out.println(doThis("d", key, "2WwJV5q1MvN4zTgO3dGLObogAPPOyxfR"));
		System.out.println(Mode.DECRYPT.generateResult(key, encrypted, false));
	}
	
}	