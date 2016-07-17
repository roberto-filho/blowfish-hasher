package org.filho.util.blowfish.hasher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.filho.util.blowfish.cipher.api.BlowfishCipher;
import org.filho.util.blowfish.shared.BlowfishCipherBuilder;
import org.filho.util.blowfish.shared.BlowfishSecurity;

public class BlowfishHasher implements BlowfishSecurity {
	
	/**
	 * The date format used to generate the date string
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	private boolean useBase64 = false;

	public String encrypt(String key, Date endDate, String data) {
		// Transform the date into a string to be encrypted together with the message
		String message = data.concat("$").concat(sdf.format(endDate));
		
		BlowfishCipherBuilder builder = createBuilderWithDefaults(Mode.ENCRYPT);
		
		try {
			return builder.build().doAction(key, message);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Could not generate a hash with the given key, date, and data: [%s, %s, %s]", key, endDate, data), e);
		}
	}
	
	public String decrypt(String key, String message) {
		// Create the builder
		BlowfishCipherBuilder builder = BlowfishBuilder.get(Mode.DECRYPT);
		
		// Set the hashing to use
		BlowfishCipher cipher = (useBase64 ? builder.useBase64() : builder.useHex()).build();
		
		try {
			String decrypted = cipher.doAction(key, message);
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Could not decrypt message [%s] with the given key: [%s]", message, key), e);
		}
	}

	public boolean isValid(String key, Date date, String hash) throws ParseException {
		// First we decrypt the message to extract the data within.
		String decrypted = decrypt(key, hash);
		// Splits the string into two
		String[] message = decrypted.split("\\$");
		
		Date dateInMessage = sdf.parse(message[0]);
		
		return DateUtils.truncatedCompareTo(date, dateInMessage, Calendar.DAY_OF_MONTH) >= 0;
	}
	
	private BlowfishCipherBuilder createBuilderWithDefaults(Mode mode) {
		BlowfishCipherBuilder builder = BlowfishBuilder.get(mode);
		// Set the option
		if(useBase64)
			builder.useBase64();
		else
			builder.useHex();
		
		return builder;
	}
	
	@Override
	public BlowfishCipherBuilder get(Mode mode) {
		return BlowfishBuilder.get(mode);
	}

}
