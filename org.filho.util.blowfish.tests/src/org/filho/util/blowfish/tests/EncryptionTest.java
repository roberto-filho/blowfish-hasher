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
	private String decryptedTestValue = "testdata";
	// TODO
	private String testHash = "TESTHASH";
	// TODO
	private String encryptedTestValue = "953fffd386a22fffd2dfffdfffd2f64451144fffd5e77fffd161257cfffd";
	
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
		String result = blowfishService.encrypt(key, endDate, decryptedTestValue);
		
		assertTrue(String.format("Expected encrypted string was [%s], but got [%s]", encryptedTestValue, result), result.equals(encryptedTestValue));
	}
	
	@Test
	public void decryptInfoWithKeyTest() {
		String decrypt = blowfishService.decrypt(key, encryptedTestValue);
		
		assertTrue(String.format("Expected decrypted string was [%s], but got [%s]", decryptedTestValue, decrypt), decrypt.equals(decryptedTestValue));
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

	
	@After
	public void tearDown() throws Exception {
	}
}
