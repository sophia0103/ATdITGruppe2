package controller;

import java.awt.Window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gemuese4you.MainFrame;
import gemuese4you.Util;
import model.UserCredentials;
import view.DataView;
import view.LoginScreenView;
import view.View;

public class LoginController implements DataController{
	private View view;

	@Override
	public void startProcess(View view) {
		PreparedStatement statement;
		ResultSet result;
		String[] data =  ((DataView) view).getData();
		UserCredentials userCredentials = createModel(data);
		
		// checks if user + password combination exists and logs in if the query was
		// successful
		String query = "SELECT * FROM users WHERE UserID = ? AND Password = ?";

		try {
			statement = Util.getConnection().prepareStatement(query);

			statement.setString(1, userCredentials.getUsername());
			statement.setString(2, userCredentials.getPassword());

			result = statement.executeQuery();

			if (result.next()) {
				JOptionPane.showMessageDialog(null, "Welcome");
				LoginScreenView.userID = userCredentials.getUsername();
				MainFrame mainScreen = new MainFrame();
				((JFrame) view).dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Username or password wrong!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public <T> T createModel(String[] inputArray) {
		UserCredentials userCredentials = new UserCredentials(inputArray[0], inputArray[1]);
		return (T) userCredentials;
	}

}
