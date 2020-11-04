package controller;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import gemuese4you.Util;
import gemuese4you.Validator;
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
	private View view;

	@Override
	public void startProcess(View view) {
		String[] inputArray = ((DataView) view).getData();
		this.view = view;
		try {
			if (Validator.isValidChangeUserCredentials(inputArray)) {
				ChangePasswordCredentials changePasswordCredentials = createModel(inputArray);
				Statement statement = Util.getConnection().createStatement();
				String queryChangePassword = "UPDATE users SET password = '"
						+ changePasswordCredentials.getNewPasswordRepeat() + "' WHERE userID ='"
						+ LoginScreenView.userID + "'";
				statement.execute(queryChangePassword);
				JOptionPane.showMessageDialog(null, Starter.content.getString("passwordChanged"));
				((ChangePasswordDialogView) view).dispose();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, Starter.content.getString("sqlStatementError"), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	@Override
	public <T> T createModel(String[] inputArray) {
		String oldPassword = inputArray[0];
		String newPassword = inputArray[1];
		String newPasswordRepeat = inputArray[2];
		ChangePasswordCredentials changePasswordCredentials = new ChangePasswordCredentials(oldPassword, newPassword,
				newPasswordRepeat);
		return (T) changePasswordCredentials;
	}

}
