package org.filho.util.blowfish.cipher;

import javax.xml.bind.DatatypeConverter;

import org.filho.util.blowfish.cipher.api.BlowfishDecrypter;
import org.filho.util.blowfish.hasher.Mode;

public class BlowfishDecrypt extends AbstractBlowfishCipher implements BlowfishDecrypter {

	private boolean base64;

	public BlowfishDecrypt(boolean useBase64) {
		this.base64 = useBase64;
	}

	@Override
	public String doAction(String key, String message) throws Exception {
		// Decode Base64 and decrypt
		byte[] decrypted = getCipher(Mode.DECRYPT, getKey(key)).doFinal(base64 ? DatatypeConverter.parseBase64Binary(message) : DatatypeConverter.parseHexBinary(message));
		String result = new String(decrypted);
		
		return result;
	}

}
