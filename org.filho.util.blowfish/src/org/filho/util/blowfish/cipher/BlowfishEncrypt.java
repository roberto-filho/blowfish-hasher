package org.filho.util.blowfish.cipher;

import javax.xml.bind.DatatypeConverter;

import org.filho.util.blowfish.hasher.Mode;

public class BlowfishEncrypt extends AbstractBlowfishCipher {

	public BlowfishEncrypt(Mode mode) {
		super(mode);
	}

	@Override
	public String doAction(String key, String message, boolean base64) throws Exception {
		byte[] messageBytes = getCipher(getMode(), getKey(key)).doFinal(message.getBytes());
		String result = base64 ? DatatypeConverter.printBase64Binary(messageBytes) : bytesToHex(messageBytes);
		return result;
	}

}
