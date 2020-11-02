package controller;

import java.awt.Window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gemuese4you.MainFrame;
import gemuese4you.Util;
import gemuese4you.Validator;
import model.UserCredentials;
import view.ChangePasswordDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

public class LoginController implements DataController {
	private View view;

	@Override
	public void startProcess(View view) {
		String[] data = ((DataView) view).getData();
		if (Validator.isValidUserLogin(data))
			try {
				UserCredentials userCredentials = createModel(data);
				if(userLogin(userCredentials)) {
					JOptionPane.showMessageDialog(null, "Welcome");
					LoginScreenView.userID = userCredentials.getUsername();
					MainFrame mainScreen = new MainFrame();
					((JFrame) view).dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or password wrong!.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException | ClassNotFoundException exception) {
				JOptionPane.showMessageDialog(null, "SQL statement failed.", "Error", JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
	}

	// checks if user + password combination exists and logs in if the query was
	// successful
	public boolean userLogin(UserCredentials userCredentials) throws SQLException, ClassNotFoundException {
		PreparedStatement statement;
		ResultSet result;

		String query = "SELECT * FROM users WHERE UserID = ? AND Password = ?";

		statement = Util.getConnection().prepareStatement(query);

		statement.setString(1, userCredentials.getUsername());
		statement.setString(2, userCredentials.getPassword());

		result = statement.executeQuery();

		return result.next();
		
	}

	@Override
	public <T> T createModel(String[] inputArray) {
		UserCredentials userCredentials = new UserCredentials(inputArray[0], inputArray[1]);
		return (T) userCredentials;
	}

}
