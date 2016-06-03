package org.filho.util.blowfish.hasher;

import javax.crypto.Cipher;

public enum Mode {
	ENCRYPT(Cipher.ENCRYPT_MODE),
	DECRYPT(Cipher.DECRYPT_MODE);
	
	private int cipherMode;

	private Mode(int cipherMode) {
		this.cipherMode = cipherMode;
	}

	public int getCipherMode() {
		return cipherMode;
	}
}