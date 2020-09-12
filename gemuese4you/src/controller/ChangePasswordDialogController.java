package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import gemuese4you.Util;
import view.ChangePasswordDialogView;
import view.LoginScreenView;

/**
 * @author I518189
 * Represents the logic of a dialog which opens when the user wants to change the password.
 */
public class ChangePasswordDialogController {
	private ChangePasswordDialogView changePasswordDialogView;
	private Connection connection;
	private JPasswordField passwordFieldOldPassword, passwordFieldNewPassword, passwordFieldNewPasswordRepeat;

	public ChangePasswordDialogController(ChangePasswordDialogView changePasswordDialogView) {
		this.changePasswordDialogView = changePasswordDialogView;

		passwordFieldOldPassword = changePasswordDialogView.getPasswordFieldOldPassword();
		passwordFieldNewPassword = changePasswordDialogView.getPasswordFieldNewPassword();
		passwordFieldNewPasswordRepeat = changePasswordDialogView.getPasswordFieldNewPasswordRepeat();

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Action which should be performed when the cancel button is clicked.
	 * @return returns a listener for the cancel button.
	 */
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changePasswordDialogView.dispose();
			}

		};
		return cancelListener;
	}

	/** Action which should be performed when the save button is clicked.
	 * @return Returns a listener for the save button.
	 */
	public ActionListener getSaveListener() {

		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String oldPassword = String.valueOf(passwordFieldOldPassword.getPassword());
					String newPassword = String.valueOf(passwordFieldNewPassword.getPassword());
					String newPasswordRepeat = String.valueOf(passwordFieldNewPasswordRepeat.getPassword());

					if (inputIsValid(oldPassword, newPassword, newPasswordRepeat)) {
						Statement statement = connection.createStatement();
						String queryChangePassword = "UPDATE user SET password = '" + newPasswordRepeat
								+ "' WHERE userID ='" + LoginScreenView.userID + "'";
						statement.execute(queryChangePassword);
						JOptionPane.showMessageDialog(null, "Password changed.");
						changePasswordDialogView.dispose();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		};
		return saveListener;
	}

	/**
	 * Checks if the input values of the input fields are valid.
	 * @param oldPassword Input value of the password field in which the user enters his old password.
	 * @param newPassword Input value of the password field in which the user enters his new password.
	 * @param newPasswordRepeat Input value of the password field in which the user enters his new password again.
	 * @return Returns true if the input values are valid, otherwise false.
	 */
	public boolean inputIsValid(String oldPassword, String newPassword, String newPasswordRepeat) {
		if (oldPassword.equals("") || newPassword.equals("") || newPasswordRepeat.equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!newPassword.equals(newPasswordRepeat)) {
			JOptionPane.showMessageDialog(null, "Please enter the same values for the new password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!oldPassword.equals(Util.getPassword())) {
			JOptionPane.showMessageDialog(null, "Old password is not correct.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (oldPassword.equals(newPasswordRepeat)) {
			JOptionPane.showMessageDialog(null, "New password can´t be the same as the old password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
