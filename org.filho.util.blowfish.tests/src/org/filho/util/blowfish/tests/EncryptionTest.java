package org.filho.util.blowfish.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.filho.util.blowfish.AbstractPluginTest;
import org.filho.util.blowfish.shared.BlowfishSecurity;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class EncryptionTest extends AbstractPluginTest {
	
	private Date endDate = new LocalDate(2016, 01, 01).toDate();
	
	private String key = "abc123";
	
	private String data = "this.is.a.dot.test";
	
	private String messageWithDate = "this.is.a.dot.test$20160101";
	
	private String encryptedValue = "6C8624A47DF3362F60A7CB95FE2AE4F85078A00CA7A17339DB9ACCF903BBF79C";
	
	// Acquire the blowfish service
	private BlowfishSecurity service;
	
	@Before
	public void setUp() throws Exception {
		service = getService(BlowfishSecurity.class);
	}

	@Test
	public void encryptInfoWithKeyTest() {
		String result = createDateHash();
		
		String failureMessage = String.format("Expected encrypted string was [%s], but got [%s]. Params: [%s, %s, %s]", encryptedValue, result, key, endDate, data);
		
		assertTrue(failureMessage, result.equals(encryptedValue));
	}
	
	@Test
	public void decryptInfoWithKeyTest() {
		// Decrypt the encrypted test value
		String decrypt = service.decrypt(key, encryptedValue);
		// Build the error message
		String errorMsg = String.format("Expected decrypted string was [%s], but got [%s]", data, decrypt);
		
		// Assert that everything is fine
		assertTrue(errorMsg, decrypt.equals(messageWithDate));
	}
	
	@Test
	public void validateInvalidDate() throws ParseException {
		// An invalid test date (after the final date)
		Date invalidDate = new LocalDate(2016, 01, 02).toDate();
		
		String dateHash = createDateHash();
		
		// Testing and invalid date
		boolean result = service.isValid(key, invalidDate, dateHash);
		
		assertFalse(String.format("Date [%s] should be invalid.", invalidDate), result);
	}
	
	public void validateEqualDate() throws ParseException {
		// An invalid test date (after the final date)
		Date equalDate = new LocalDate(2016, 01, 02).toDate();
		
		String dateHash = createDateHash();
		
		// Testing and invalid date
		boolean result = service.isValid(key, equalDate, dateHash);
		
		assertTrue(String.format("Date [%s] should be valid.", equalDate), result);
	}
	
	@Test
	public void validateValidDate() throws ParseException {
		// A valid date for testing (immediately before the valid date)
		Date validDate = new LocalDate(2015, 12, 31).toDate();
		
		String dateHash = createDateHash();
		
		// Testing a valid date
		boolean result = service.isValid(key, validDate, dateHash);
		
		assertTrue(String.format("Date [%s] should be valid.", validDate), result);
		
	}

	private String createDateHash() {
		// Encrypt the hash
		String dateHash = service.encrypt(key, endDate, data);
		return dateHash;
	}
}
