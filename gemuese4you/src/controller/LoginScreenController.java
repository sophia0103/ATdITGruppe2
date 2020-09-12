package controller;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gemuese4you.MainScreen;
import gemuese4you.Util;
import view.LoginScreenView;
import view.RegisterView;

import javax.swing.*;

public class LoginScreenController {

	//declaring variables
	
	LoginScreenView loginScreenView;
	private Connection connection;
	public static String userID;

	MainScreen mainScreen;

	private JPasswordField textFieldPassword;
	private JTextField textFieldUser;

	// constructor to set up a data base connection
	// and to provide written text from the textfields to use for querys
	public LoginScreenController(LoginScreenView loginScreenView) {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connection == null) {
			JOptionPane.showMessageDialog(null, "No connection to data base available!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		this.loginScreenView = loginScreenView;

		textFieldUser = loginScreenView.getTextFieldUser();
		textFieldPassword = loginScreenView.getPasswordFieldPassword();
	}

	// when you click the Register Button
	// for doing a new registration
	public ActionListener getRegisterListener() {
		ActionListener registerListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				loginScreenView.dispose();

				RegisterView registerView = new RegisterView();
			}
		};
		return registerListener;
	}

	// button function to try to log into the program with a Username and Password
	public ActionListener getLoginListener() {
		ActionListener loginListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				PreparedStatement statement;
				ResultSet result;
				String user = textFieldUser.getText();
				String pass = String.valueOf(textFieldPassword.getPassword());
				// checks if user + password combination exists and logs in if the query was
				// successful
				String query = "SELECT * FROM user WHERE UserID = ? AND Password = ?";

				try {
					statement = Util.getConnection().prepareStatement(query);

					statement.setString(1, user);
					statement.setString(2, pass);

					result = statement.executeQuery();

					if (result.next()) {
						JOptionPane.showMessageDialog(null, "Welcome");
						LoginScreenView.userID = user;
						mainScreen = new MainScreen();
						loginScreenView.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Username or password wrong!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		return loginListener;
	}
}