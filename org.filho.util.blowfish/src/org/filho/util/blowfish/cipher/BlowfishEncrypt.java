package org.filho.util.blowfish.cipher;

import javax.xml.bind.DatatypeConverter;

import org.filho.util.blowfish.cipher.api.BlowfishEncrypter;
import org.filho.util.blowfish.hasher.Mode;

public class BlowfishEncrypt extends AbstractBlowfishCipher implements BlowfishEncrypter {
	
	private boolean base64;
	
	public BlowfishEncrypt(boolean base64) {
		this.base64 = base64;
	}

	@Override
	public String doAction(String key, String message) throws Exception {
		byte[] messageBytes = getCipher(Mode.ENCRYPT, getKey(key)).doFinal(message.getBytes());
		String result = base64 ? DatatypeConverter.printBase64Binary(messageBytes) : bytesToHex(messageBytes);
		return result;
	}

}
