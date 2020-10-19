package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import gemuese4you.Util;
import model.ChangePasswordCredentials;
import view.ChangePasswordDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author I518189 Represents the logic of a dialog which opens when the user
 *         wants to change the password.
 */
public class ChangePasswordDialogController implements DataController {
	private Connection connection;
	private View view;
	private String oldPassword, newPassword, newPasswordRepeat;

	public ChangePasswordDialogController() {

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void startProcess(View view) {
		setView(view);
		
		ChangePasswordCredentials data = ((DataView) view).getData();
		oldPassword = data.getOldPassword();
		newPassword = data.getNewPassword();
		newPasswordRepeat = data.getNewPasswordRepeat();
		try {
			if (checkInputValidity()) {
				Statement statement = connection.createStatement();
				String queryChangePassword = "UPDATE users SET password = '" + newPasswordRepeat
						+ "' WHERE userID ='" + LoginScreenView.userID + "'";
				statement.execute(queryChangePassword);
				JOptionPane.showMessageDialog(null, "Password changed.");
				((ChangePasswordDialogView)view).dispose();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}



	@Override
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Checks if the input values of the input fields are valid.
	 * @param oldPassword Input value of the password field in which the user enters his old password.
	 * @param newPassword Input value of the password field in which the user enters his new password.
	 * @param newPasswordRepeat Input value of the password field in which the user enters his new password again.
	 * @return Returns true if the input values are valid, otherwise false.
	 */
	@Override
	public boolean checkInputValidity() {
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
