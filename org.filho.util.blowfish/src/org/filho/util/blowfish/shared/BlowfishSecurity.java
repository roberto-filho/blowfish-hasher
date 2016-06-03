package org.filho.util.blowfish.shared;

import java.util.Date;

import org.filho.util.blowfish.hasher.Mode;

public interface BlowfishSecurity {

	BlowfishCipherBuilder get(Mode mode);
	
	String encrypt(String key, Date endDate, String data);
	String decrypt(String key, String message);

	boolean isValid(String key, Date date, String hash);
	
}
