package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gemuese4you.Util;
import view.LoginScreenView;
import view.RegisterView;

public class RegisterController {

	//decalring variables
	
	RegisterView registerView;
	private JTextField textFieldUser, textFieldIsFarmer;
	private JPasswordField passwordFieldPassword, passwordFieldRePassword;

	private Connection connection;

	
	//constructor to create a data base connection
	// and to provide written text from the textfields to use for querys
	public RegisterController(RegisterView registerView) {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connection == null) {
			JOptionPane.showMessageDialog(null, "No connection to data base available!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		this.registerView = registerView;

		textFieldUser = registerView.getTextFieldUser();
		passwordFieldPassword = registerView.getPasswordFieldPassword();
		textFieldIsFarmer = registerView.getTextFieldIsFarmer();
		passwordFieldRePassword = registerView.getPasswordFieldRePassword();

	}

	// checks, if Username already exists in data base
	public boolean checkUsername(String username) {

		PreparedStatement statement;
		ResultSet result;
		boolean checkUser = false;

		String query = "SELECT * FROM user WHERE UserID = ?";

		try {
			statement = Util.getConnection().prepareStatement(query);
			statement.setString(1, username);

			result = statement.executeQuery();

			if (result.next()) {
				checkUser = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkUser;
	}

	
	//button function to cancel the Registration
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				LoginScreenView log = new LoginScreenView();
				
				registerView.dispose();
			}
		};
		return cancelListener;
	}

	// button function for register confirmation
	public ActionListener getRegistrationListener() {
		ActionListener registerListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String user = textFieldUser.getText();
				String pass = String.valueOf(passwordFieldPassword.getPassword());
				String repass = String.valueOf(passwordFieldRePassword.getPassword());
				String farmer = textFieldIsFarmer.getText();
				String yesFarmer = "Yes";
				String nofarmer = "No";
				int isFarmer = 0;
				if (farmer.contentEquals(yesFarmer)) {
					isFarmer = 1;
				} else {
					isFarmer = 0;
				}

				if (user.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in a username");
				} else if (pass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in a password");
				} else if (!pass.equals(repass)) {
					JOptionPane.showMessageDialog(null, "Please retype your password");
				} else if (farmer.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in, if you are a farmer(Yes) or not(No)");
				} else if (checkUsername(user)) {
					JOptionPane.showMessageDialog(null, "Username already exists!");
				} else {

					PreparedStatement statement;
					String query = "INSERT INTO USER (UserID, Password, isFarmer) VALUES (?, ?, ?)";

					try {
						statement = Util.getConnection().prepareStatement(query);
						statement.setString(1, user);
						statement.setString(2, pass);
						statement.setString(3, String.valueOf(isFarmer));

						if (statement.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "New User Added");
							LoginScreenView log = new LoginScreenView();
							registerView.dispose();
						}

					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		};
		return registerListener;
	}
}
