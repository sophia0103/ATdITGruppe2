package gemuese4you;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.JOptionPane;

public class Validator {
	private static Validator validator;
	
	private static Validator getValidator(){
		if (validator == null) {
			validator = new Validator();
		}
		return validator;
	}
	
	private boolean isInputEmpty(String input) {
		if (input.equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return true;
		}
		return false;
	}

	private boolean isInputString(String input) {
		try {
			Integer.parseInt(input);
			JOptionPane.showMessageDialog(null, "Input has to be String.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean isInputNumeric(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Input has to be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public static boolean isValidOffer(String[] inputArray) {
		if (getValidator().isInputEmpty(inputArray[0]) || getValidator().isInputEmpty(inputArray[1])
				|| getValidator().isInputEmpty(inputArray[2]) || getValidator().isInputEmpty(inputArray[3])) {
			return false;
		}
		//represents int price
		if (!getValidator().isInputNumeric(inputArray[0])) {
			return false;
		}
		//represents int distance
		if (!getValidator().isInputNumeric(inputArray[1])) {
			return false;
		}
		//represents String date
		if (!getValidator().isInputString(inputArray[2])) {
			return false;
		}
		if (!getValidator().isInputString(inputArray[3])) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidJob(String[] inputArray) {
		if (getValidator().isInputEmpty(inputArray[0]) || getValidator().isInputEmpty(inputArray[1])
				|| getValidator().isInputEmpty(inputArray[2]) || getValidator().isInputEmpty(inputArray[3])
				|| getValidator().isInputEmpty(inputArray[4]) || getValidator().isInputEmpty(inputArray[5]) 
				|| getValidator().isInputEmpty(inputArray[6])) {
			return false;
		}
		//represents int distance
		if (!getValidator().isInputNumeric(inputArray[1])) {
			return false;
		}
		//represents int duration
		if (!getValidator().isInputNumeric(inputArray[2])) {
			return false;
		}
		
		//represents int salary
		if (!getValidator().isInputNumeric(inputArray[3])) {
			return false;
		}
		//represents String date
		if (!getValidator().isInputString(inputArray[4])) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidChangeUserCredentials(String[] inputArray) {
		if (getValidator().isInputEmpty(inputArray[0]) || getValidator().isInputEmpty(inputArray[1])
				|| getValidator().isInputEmpty(inputArray[2])) {
			return false;
		}
		if (!inputArray[1].equals(inputArray[2])) {
			JOptionPane.showMessageDialog(null, "Please enter the same values for the new password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!inputArray[0].equals(Util.getPassword())) {
			JOptionPane.showMessageDialog(null, "Old password is not correct.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (inputArray[0].equals(inputArray[1])) {
			JOptionPane.showMessageDialog(null, "New password canÂ´t be the same as the old password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static boolean isValidUserLogin(String[] inputArray) {
		if (getValidator().isInputEmpty(inputArray[0]) || getValidator().isInputEmpty(inputArray[1])) {
			return false;
		}

		return true;
	}

	public static boolean isValidRegistration(String[] inputArray) {
		if (getValidator().isInputEmpty(inputArray[0]) || getValidator().isInputEmpty(inputArray[1])
				|| getValidator().isInputEmpty(inputArray[2]) || getValidator().isInputEmpty(inputArray[3])) {
			return false;
		}
		if (Util.checkUsername(inputArray[0])) {
			JOptionPane.showMessageDialog(null, "Username is already existing, please try another one.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!inputArray[1].equals(inputArray[2])) {
			JOptionPane.showMessageDialog(null, "Please enter the same values for the password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
	
}
