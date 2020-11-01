package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gemuese4you.Util;
import model.User;
import view.DataView;
import view.LoginScreenView;
import view.View;

public class RegisterController implements DataController{
	private View view;

	@Override
	public void startProcess(View view) {
		String[] data = ((DataView) view).getData();
		User user = createModel(data);
		
		String repass = data[3];
		

//		if (user.equals("")) {
//			JOptionPane.showMessageDialog(null, "Please fill in a username");
//		} else if (user.getPassword().equals("")) {
//			JOptionPane.showMessageDialog(null, "Please fill in a password");
//		} else if (!user.getPassword().equals(repass)) {
//			JOptionPane.showMessageDialog(null, "Please retype your password");
//		} else if (user.getIsFarmer().equals("")) {
//			JOptionPane.showMessageDialog(null, "Please fill in, if you are a farmer(Yes) or not(No)");
//		} else if (checkUsername(user)) {
//			JOptionPane.showMessageDialog(null, "Username already exists!");
//		} else {

		PreparedStatement statement;
		String query = "INSERT INTO users (UserID, Password, isFarmer) VALUES (?, ?, ?)";

		try {
			statement = Util.getConnection().prepareStatement(query);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getIsFarmer());

			if (statement.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "New User Added");
				LoginScreenView log = new LoginScreenView();
				((JFrame) view).dispose();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
	}

	@Override
	public <T> T createModel(String[] inputArray) {
		User user = new User(inputArray[0], inputArray[1], Integer.parseInt(inputArray[2]));
		return (T) user;
	}

}
