package org.filho.util.blowfish.shared;

import org.filho.util.blowfish.cipher.api.BlowfishCipher;

public interface BlowfishCipherBuilder {
	BlowfishCipher build();
	
	BlowfishCipherBuilder useBase64();
	BlowfishCipherBuilder useHex();
}
