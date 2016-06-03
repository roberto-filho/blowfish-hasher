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
	
	private Date endDate;
	
	private String key = "abc123";
	private String testValue = "this.is.a.dot.test";
	
	private String encryptionResult = "6C8624A47DF3362F60A7CB95FE2AE4F85078A00CA7A17339DB9ACCF903BBF79C";
	
	private BlowfishSecurity blowfishService;
	private String dateHash;

	@Before
	public void setUp() throws Exception {
		// Acquire the blowfish service
		blowfishService = new BlowfishHasher();
		
		// The final valid date to be encrypted
		endDate = new LocalDate(2016, 01, 01).toDate();
	}

	@Test
	public void encryptInfoWithKeyTest() {
		String result = blowfishService.encrypt(key, endDate, testValue);
		
		String failureMessage = String.format("Expected encrypted string was [%s], but got [%s]. Params: [%s, %s, %s]", encryptionResult, result, key, endDate, testValue);
		
		assertTrue(failureMessage, result.equals(encryptionResult));
	}
	
	@Test
	public void decryptInfoWithKeyTest() {
		String decrypt = blowfishService.decrypt(key, encryptionResult);
		
		assertTrue(String.format("Expected decrypted string was [%s], but got [%s]", testValue, decrypt), decrypt.equals(testValue));
	}
	
	@Test
	public void validateHashTest() {
		// A valid date for testing (immediately before the valid date)
		Date validDate = new LocalDate(2016, 12, 31).toDate();
		// An invalid test date (after the final date)
		Date invalidDate = new LocalDate(2016, 01, 02).toDate();
		
		// Testing a valid date
		boolean result = blowfishService.isValid(key, validDate, dateHash);
		
		assertTrue(String.format("Date [%s] should be valid.", validDate), result);
		
		// Testing and invalid date
		result = blowfishService.isValid(key, invalidDate, dateHash);
		
		assertFalse(String.format("Date [%s] should be invalid.", invalidDate), result);
	}
	
	public static void main(String[] args) throws Exception {
		EncryptionTest test = new EncryptionTest();
		test.setUp();
		
		test.encryptInfoWithKeyTest();
	}

	
	@After
	public void tearDown() throws Exception {
	}
}
