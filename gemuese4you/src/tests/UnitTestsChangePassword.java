package tests;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import controller.ChangePasswordDialogController;
import gemuese4you.LoginScreen;
import gemuese4you.Util;
import view.ChangePasswordDialogView;

/**
 * @author I518189
 * Unit Tests for the method inputIsValid of the class ChangePasswordDialogController.
 */
class UnitTestsChangePassword {

	/**
	 * Tests if the method inputIsValid reacts correctly for the input of different new passwords.
	 */
	@Test
	void testIsInputValidWrongPasswordRepeated() {
		ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(
				changePasswordDialogView);
		LoginScreen.userID = "sophia";
		Util util = new Util();
		String password = Util.getPassword();
		assertFalse(changePasswordDialogController.inputIsValid(password, "def", "defg"));
		assertTrue(changePasswordDialogController.inputIsValid(password, "def", "def"));
	}

	/**
	 * Tests if the method inputIsValid reacts correctly for the same old and new password.
	 */
	@Test
	void testIsInputValidSamePassword() {
		ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(
				changePasswordDialogView);
		LoginScreen.userID = "sophia";
		Util util = new Util();
		String password = Util.getPassword();

		assertFalse(changePasswordDialogController.inputIsValid(password, password, password));
		assertTrue(changePasswordDialogController.inputIsValid(password, "abc", "abc"));
	}

	/**
	 * Tests if the method inputIsValid reacts correctly for empty input.
	 */
	@Test
	void testIsInputValidWrongEmptyInput() {
		ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(
				changePasswordDialogView);
		LoginScreen.userID = "sophia";
		Util util = new Util();
		String password = Util.getPassword();

		assertFalse(changePasswordDialogController.inputIsValid("", "", ""));
		assertTrue(changePasswordDialogController.inputIsValid(password, "def", "def"));
	}

	/**
	 * Tests if the method inputIsValid reacts correctly for the incorrect old password.
	 */
	@Test
	void testIsInputValidWrongOldPasswordIncorrect() {
		ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(
				changePasswordDialogView);
		LoginScreen.userID = "sophia";
		Util util = new Util();
		String password = Util.getPassword();

		assertFalse(changePasswordDialogController.inputIsValid("def", "fgh", "fgh"));
		assertTrue(changePasswordDialogController.inputIsValid(password, "fgh", "fgh"));
	}

}
