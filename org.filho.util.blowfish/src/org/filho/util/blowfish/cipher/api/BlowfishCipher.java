package org.filho.util.blowfish.cipher.api;

public interface BlowfishCipher {
	String doAction(String key, String message) throws Exception;
}