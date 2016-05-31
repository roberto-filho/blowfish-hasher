package org.filho.util.blowfish.shared;

import java.util.Date;

public interface BlowfishSecurity {

	String encrypt(String key, Date endDate, String data);
	String decrypt(String key, String message);

	boolean isValid(String key, Date date, String hash);
	
}
