package org.filho.util.blowfish.hasher;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.filho.util.blowfish.cipher.api.BlowfishCipher;
import org.filho.util.blowfish.shared.BlowfishCipherBuilder;
import org.filho.util.blowfish.shared.BlowfishSecurity;

public class BlowfishHasher implements BlowfishSecurity {
	
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
		// Set the default base64 option
		BlowfishCipher cipher = builder.useBase64().build();
		try {
			String decrypted = cipher.doAction(key, message);
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Could not decrypt message [%s] with the given key: [%s]", message, key), e);
		}
	}

	public boolean isValid(String key, Date date, String hash) {
		// TODO Auto-generated method stub
		return false;
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
