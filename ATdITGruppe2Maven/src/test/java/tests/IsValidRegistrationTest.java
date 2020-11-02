package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gemuese4you.Util;
import gemuese4you.Validator;
import view.LoginScreenView;


public class IsValidRegistrationTest {

	/**
	 * This test tests if empty input is invalid.
	 */
	@Test
	void testEmptyInput() {
		String[] inputArray = new String[4];
		inputArray[0] = "";
		inputArray[1] = "";
		inputArray[2] = "";
		inputArray[3] = "";
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}
	
//	/**
//	 * This test tests if a username can be taken twice.
//	 */
//	@Test
//	void testSameUsername() {
//		String[] inputArray = new String[4];
//		inputArray[0] = "Martin";
//		inputArray[1] = "def";
//		inputArray[2] = "defg";
//		inputArray[3] = "Yes";
//		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
//	}
	
	
	
	/**
	 * This test tests if the entry of two different new passwords is invalid.
	 */
	@Test
	void testDifferentPasswords() {
		LoginScreenView.userID = "Martin";
		String[] inputArray = new String[4];
		inputArray[0] = "abc";
		inputArray[1] = "def";
		inputArray[2] = "defg";
		inputArray[3] = "1";
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}
	
	
	/**
	 * This test tests if valid inputs are identified as such by the Validator if the data base is empty.
	 */
	@Test
	void testValidInput() {
		String[] inputArray = new String[4];
		inputArray[0] = "JUnitTestName";
		inputArray[1] = "testPassword";
		inputArray[2] = "testPassword";
		inputArray[2] = "1";
		assertTrue(Validator.isValidChangeUserCredentials(inputArray));
	}
	
}
