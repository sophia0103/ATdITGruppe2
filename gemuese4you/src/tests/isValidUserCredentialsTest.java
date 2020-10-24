package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gemuese4you.Util;
import gemuese4you.Validator;
import view.LoginScreenView;

/**
 * @author I518189
 * This class tests the funcionality of the isValidChangeUserCredentials method of the Validator class.
 */
class isValidUserCredentialsTest {

	/**
	 * This test tests if empty input is invalid.
	 */
	@Test
	void testEmptyInput() {
		String[] inputArray = new String[3];
		inputArray[0] = "";
		inputArray[1] = "";
		inputArray[2] = "";
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}
	
	/**
	 * This test tests if the entry of two different new passwords is invalid.
	 */
	@Test
	void testDifferentNewPasswords() {
		LoginScreenView.userID = "sophia";
		String[] inputArray = new String[3];
		inputArray[0] = Util.getPassword();
		inputArray[1] = "def";
		inputArray[2] = "defg";
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}
	
	/**
	 * This test tests if a false old password is invalid.
	 */
	@Test
	void testInvalidOldPassword() {
		String[] inputArray = new String[3];
		inputArray[0] = "abc";
		inputArray[1] = "def";
		inputArray[2] = "def";
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}
	
	/**
	 * This test tests if the old and new password are the same.
	 */
	@Test
	void testOldPasswordIsNewPassword() {
		LoginScreenView.userID = "sophia";
		String[] inputArray = new String[3];
		inputArray[0] = Util.getPassword();
		inputArray[1] = Util.getPassword();
		inputArray[2] = Util.getPassword();
		assertFalse(Validator.isValidChangeUserCredentials(inputArray));
	}

	/**
	 * This test tests if valid inputs are identified as such by the Validator.
	 */
	@Test
	void testValidInput() {
		LoginScreenView.userID = "sophia";
		String[] inputArray = new String[3];
		inputArray[0] = Util.getPassword();
		inputArray[1] = "def";
		inputArray[2] = "def";
		assertTrue(Validator.isValidChangeUserCredentials(inputArray));
	}
}
