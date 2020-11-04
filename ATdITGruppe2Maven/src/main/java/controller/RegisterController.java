package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gemuese4you.Starter;
import gemuese4you.Util;
import gemuese4you.Validator;
import model.User;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author I518189 Represents the logic behind the registration process.
 */
public class RegisterController implements DataController {
	private View view;


	@Override
	public void startProcess(View view) {
		String[] data = ((DataView) view).getData();

		if (Validator.isValidRegistration(data)) {
			try {
				User user = createModel(data);
				userRegistration(user);
				((JFrame) view).dispose();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}

	}

	/**
	 * Adds the user model to the database
	 * @param user The user model that is added to the database.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void userRegistration(User user) throws SQLException {

		PreparedStatement statement;
		String query = "INSERT INTO users (UserID, Password, isFarmer) VALUES (?, ?, ?)";

		statement = Util.getConnection().prepareStatement(query);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setInt(3, user.getIsFarmer());

		if (statement.executeUpdate() > 0) {
			JOptionPane.showMessageDialog(null, Starter.content.getString("newUserAdded"));
			LoginScreenView log = new LoginScreenView();
		}

	}


	@Override
	public <T> T createModel(String[] inputArray) {
		User user = new User(inputArray[0], inputArray[1], Integer.parseInt(inputArray[3]));
		return (T) user;
	}

}
