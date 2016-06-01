package org.filho.util.blowfish.cipher;

public interface BlowfishCipher {
	String doAction(String key, String message, boolean base64) throws Exception;
}