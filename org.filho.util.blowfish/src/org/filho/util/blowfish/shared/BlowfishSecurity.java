package org.filho.util.blowfish.shared;

import java.text.ParseException;
import java.util.Date;

import org.filho.util.blowfish.hasher.Mode;

public interface BlowfishSecurity {

	/**
	 * Creates a cipher builder.
	 * @param mode what this cipher will do: encrypt or decrypt 
	 * @return a builder for encrypting/decrypting stuff
	 * @see Mode
	 */
	BlowfishCipherBuilder get(Mode mode);
	
	/**
	 * Encrypts.
	 * @param key the key to use for encryption
	 * @param endDate the date to encode in the encryption for isValid calls.
	 * @param data the data (also called message) to encrypt along with the date
	 * @return a String representation
	 */
	String encrypt(String key, Date endDate, String data);
	
	/**
	 * Decrypts the message and returns the data contained. The
	 * data contained usually is in the form of a date and some other
	 * arbitrary string, as per the method {@link #encrypt(String, Date, String)}
	 * @param key
	 * @param message
	 * @return the data contained in this message
	 */
	String decrypt(String key, String message);

	/**
	 * Checks if a given date is valid for the hash.
	 * @param key the key to use for decryption
	 * @param date the date to check against
	 * @param hash message encrypted with the {@link #encrypt(String, Date, String)} method.
	 * @return <code>true</code> if the hash has a valid date.
	 * @throws ParseException when the hash does no contain a valid date
	 */
	boolean isValid(String key, Date date, String hash) throws ParseException;
	
}
