package org.filho.util.blowfish.hasher;

import org.filho.util.blowfish.cipher.BlowfishDecrypt;
import org.filho.util.blowfish.cipher.BlowfishEncrypt;
import org.filho.util.blowfish.cipher.api.BlowfishCipher;
import org.filho.util.blowfish.shared.BlowfishCipherBuilder;

public class BlowfishBuilder implements BlowfishCipherBuilder {

	private boolean useBase64;
	private Mode mode;
	
	public static BlowfishCipherBuilder get(Mode mode) {
		return new BlowfishBuilder(mode);
	}
	
	/**
	 * Private constructor. Used only to force the mode parameter.
	 * @param mode encrypt or decrypt
	 * @see Mode#ENCRYPT
	 * @see Mode#DECRYPT
	 */
	private BlowfishBuilder(Mode mode) {
		this.mode = mode;
	}

	@Override
	public BlowfishCipher build() {
		// Create an encrypter or a decrypter
		if(mode == Mode.ENCRYPT) {
			// Do the necessary encoding
			return new BlowfishEncrypt(useBase64);
		} else {
			return new BlowfishDecrypt(useBase64);
		}
	}
	
	@Override
	public BlowfishCipherBuilder useBase64() {
		return useBase64(true);
	}
	
	@Override
	public BlowfishCipherBuilder useHex() {
		return useBase64(false);
	}
	
	private BlowfishCipherBuilder useBase64(boolean useBase64) {
		this.useBase64 = useBase64;
		return this;
	}

}
