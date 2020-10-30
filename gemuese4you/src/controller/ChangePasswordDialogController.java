package controller;

import java.sql.Statement;

import javax.swing.JOptionPane;

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
		setView(view);
		try {
			if (Validator.isValidChangeUserCredentials(inputArray)) {
				ChangePasswordCredentials changePasswordCredentials = createModel(inputArray);
				Statement statement = Util.getConnection().createStatement();
				String queryChangePassword = "UPDATE users SET password = '"
						+ changePasswordCredentials.getNewPasswordRepeat() + "' WHERE userID ='"
						+ LoginScreenView.userID + "'";
				statement.execute(queryChangePassword);
				JOptionPane.showMessageDialog(null, "Password changed.");
				((ChangePasswordDialogView) view).dispose();
			}
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}

	}

	@Override
	public void setView(View view) {
		this.view = view;
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
