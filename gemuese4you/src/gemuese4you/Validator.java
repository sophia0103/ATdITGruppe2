package gemuese4you;

import java.util.Objects;

import javax.swing.JOptionPane;

public class Validator {

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
			JOptionPane.showMessageDialog(null, "Input has to be String.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public static boolean isValidOffer(String[] inputArray) {
		Validator validator = new Validator();
		if (validator.isInputEmpty(inputArray[0]) || validator.isInputEmpty(inputArray[1])
				|| validator.isInputEmpty(inputArray[2])) {
			return false;
		}
		if (!validator.isInputNumeric(inputArray[0])) {
			return false;
		}
		if (!validator.isInputNumeric(inputArray[1])) {
			return false;
		}
		if (!validator.isInputString(inputArray[2])) {
			return false;
		}
		return true;
	}

}
