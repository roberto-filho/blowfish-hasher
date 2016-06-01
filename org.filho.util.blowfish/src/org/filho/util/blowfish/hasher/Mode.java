package org.filho.util.blowfish.hasher;

import javax.crypto.Cipher;

import org.filho.util.blowfish.cipher.BlowfishCipher;
import org.filho.util.blowfish.cipher.BlowfishDecrypt;
import org.filho.util.blowfish.cipher.BlowfishEncrypt;

public enum Mode implements BlowfishCipher {
	ENCRYPT(Cipher.ENCRYPT_MODE, BlowfishEncrypt.class),
	DECRYPT(Cipher.DECRYPT_MODE, BlowfishDecrypt.class);
	
	private int cipherMode;
	private BlowfishCipher cipherType;

	private Mode(int cipherMode, Class<? extends BlowfishCipher> clazz) {
		this.cipherMode = cipherMode;
		try {
			this.cipherType = clazz.getConstructor(Mode.class).newInstance(this);
		} catch (Exception e) {/*Do nothing*/}
	}
	
	public int getCipherMode() {
		return cipherMode;
	}
	
	public String generateResult(String key, String message, boolean base64) throws Exception {
		if(this == ENCRYPT)
			return cipherType.doAction(key, message, base64);
		else
			return cipherType.doAction(key, message, base64);
	}
	
	@Override
	public String doAction(String key, String message, boolean base64) throws Exception {
		return cipherType.doAction(key, message, base64);
	}
}