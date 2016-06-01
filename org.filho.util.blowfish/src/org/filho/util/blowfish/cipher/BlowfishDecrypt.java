package org.filho.util.blowfish.cipher;

import javax.xml.bind.DatatypeConverter;

import org.filho.util.blowfish.hasher.Mode;

public class BlowfishDecrypt extends AbstractBlowfishCipher {

	public BlowfishDecrypt(Mode mode) {
		super(mode);
	}

	@Override
	public String doAction(String key, String message, boolean base64) throws Exception {
		// Decode Base64 and decrypt
		byte[] decrypted = getCipher(getMode(), getKey(key)).doFinal(base64 ? DatatypeConverter.parseBase64Binary(message) : DatatypeConverter.parseHexBinary(message));
		String result = new String(decrypted);
		return result;
	}

}
