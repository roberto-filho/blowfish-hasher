package org.filho.util.blowfish.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.filho.util.blowfish.hasher.BlowfishHasher;
import org.filho.util.blowfish.shared.BlowfishSecurity;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EncryptionTest {
	
	private Date endDate = new LocalDate(2016, 01, 01).toDate();
	
	private String key = "abc123";
	
	private String data = "this.is.a.dot.test";
	
	private String messageWithDate = "this.is.a.dot.test$20160101";
	
	private String encryptedValue = "6C8624A47DF3362F60A7CB95FE2AE4F85078A00CA7A17339DB9ACCF903BBF79C";
	
	// Acquire the blowfish service
	private BlowfishSecurity service = new BlowfishHasher();
	
	private String dateHash;

	@Before
	public void setUp() throws Exception { }

	@Test
	public void encryptInfoWithKeyTest() {
		String result = service.encrypt(key, endDate, data);
		
		
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
	public void validateHashTest() {
		// A valid date for testing (immediately before the valid date)
		Date validDate = new LocalDate(2016, 12, 31).toDate();
		// An invalid test date (after the final date)
		Date invalidDate = new LocalDate(2016, 01, 02).toDate();
		
		// Testing a valid date
		boolean result = service.isValid(key, validDate, dateHash);
		
		assertTrue(String.format("Date [%s] should be valid.", validDate), result);
		
		// Testing and invalid date
		result = service.isValid(key, invalidDate, dateHash);
		
		assertFalse(String.format("Date [%s] should be invalid.", invalidDate), result);
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
