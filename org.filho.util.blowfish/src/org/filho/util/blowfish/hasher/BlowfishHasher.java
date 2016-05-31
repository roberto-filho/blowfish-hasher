package org.filho.util.blowfish.hasher;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.filho.util.blowfish.shared.BlowfishSecurity;

public class BlowfishHasher implements BlowfishSecurity {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Override
	public String encrypt(String key, Date endDate, String data) {
		// Transform the date into a string to be encrypted together with the message
		AbstractBlowfishHasher hasher = new AbstractBlowfishHasher(key);
		
		String message = sdf.format(endDate).concat("$").concat(data);
		
		try {
			return hasher.encodeHash(message);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Could not generate a hash with the given key, date, and data: [%s, %s, %s]", key, endDate, data), e);
		}
	}
	
	@Override
	public String decrypt(String key, String message) {
		AbstractBlowfishHasher hasher = new AbstractBlowfishHasher(key);
		return hasher.convertHexToString(message);
	}

	@Override
	public boolean isValid(String key, Date date, String hash) {
		// TODO Auto-generated method stub
		return false;
	}

}
