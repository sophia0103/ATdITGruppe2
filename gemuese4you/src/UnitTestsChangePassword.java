import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.ChangePasswordDialogController;
import junit.framework.Assert;
import view.ChangePasswordDialogView;

class UnitTestsChangePassword {
	
	@Test
	void testIsInputValidWrongPasswordRepeated() {
		ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(changePasswordDialogView);

		assertFalse(changePasswordDialogController.inputIsValid("abc", "def", "defg"));	
		assertTrue(changePasswordDialogController.inputIsValid("abc", "def", "def"));	
	}
	
	@Test
	void testIsInputValidSamePassword() {
		 ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		 ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(changePasswordDialogView);
		assertFalse(changePasswordDialogController.inputIsValid("abc", "abc", "abc"));	
		assertTrue(changePasswordDialogController.inputIsValid("abc", "def", "def"));	
	}
	
	@Test
	void testIsInputValidWrongEmptyInput() {
		 ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		 ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(changePasswordDialogView);

		assertFalse(changePasswordDialogController.inputIsValid("", "", ""));	
		assertTrue(changePasswordDialogController.inputIsValid("abc", "def", "def"));	
	}
	
	@Test
	void testIsInputValidWrongOldPasswordIncorrect() {
		 ChangePasswordDialogView changePasswordDialogView = new ChangePasswordDialogView();
		 ChangePasswordDialogController changePasswordDialogController = new ChangePasswordDialogController(changePasswordDialogView);

		String password = "abc";
		assertFalse(changePasswordDialogController.inputIsValid("def", "fgh", "fgh"));	
		assertTrue(changePasswordDialogController.inputIsValid(password, "fgh", "fgh"));	
	}

}
