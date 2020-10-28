package view;

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

import controller.Controller;
import controller.RegisterCancelController;
import controller.RegisterController;
import gemuese4you.Util;

public class RegisterView extends JFrame implements DataView{

	
	//declaring variables
	Container container;
	static Connection connection;

	private JTextField textFieldUser, textFieldIsFarmer;
	private JPasswordField passwordFieldPassword, passwordFieldRePassword;
	private JLabel labelUser, labelPassword, labelRePassword, labelIsFarmer;
	private JPanel panelRegister, panelButton;
	private JButton buttonCancel, buttonRegister;
	private Controller registerCancelController;
	private Controller registerController;

	
	//constructor to create a Screen with below components
	public RegisterView() {

		container = getContentPane();
		container.setLayout(new BorderLayout());
		panelRegister = new JPanel(new GridLayout(8, 1));
		panelButton = new JPanel(new GridLayout(1, 2));
		textFieldUser = new JTextField();
		passwordFieldPassword = new JPasswordField();
		passwordFieldRePassword = new JPasswordField();
		textFieldIsFarmer = new JTextField();
		labelUser = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");
		labelRePassword = new JLabel("Confirm Password: ");
		labelIsFarmer = new JLabel("Farmer? (Yes/No)");

		panelRegister.add(labelUser);
		panelRegister.add(textFieldUser);
		panelRegister.add(labelPassword);
		panelRegister.add(passwordFieldPassword);
		panelRegister.add(labelRePassword);
		panelRegister.add(passwordFieldRePassword);
		panelRegister.add(labelIsFarmer);
		panelRegister.add(textFieldIsFarmer);

		panelRegister.setBackground(Util.orange);
		panelButton.setBackground(Util.orange);
		
		registerCancelController = new RegisterCancelController();
		
		buttonRegister = new JButton("Confirm Registration");
		registerController = new RegisterController();
		buttonRegister.addActionListener(e -> registerController.startProcess(this));
		buttonCancel = new JButton("Cancel");
		registerCancelController = new RegisterCancelController();
		buttonCancel.addActionListener(e -> registerCancelController.startProcess(this));
		
		panelButton.add(buttonRegister);
		panelButton.add(buttonCancel);



		container.add(panelRegister, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Register");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	//give getter methods for easy access to text in textfields
	
	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public JTextField getTextFieldIsFarmer() {
		return textFieldIsFarmer;
	}

	public JPasswordField getPasswordFieldPassword() {
		return passwordFieldPassword;
	}

	public JPasswordField getPasswordFieldRePassword() {
		return passwordFieldRePassword;
	}

	@Override
	public String[] getData() {
		String[] data = new String[4];
		data[0] = textFieldUser.getText();
		data[1] = String.valueOf(passwordFieldPassword.getPassword());
		String isFarmer = textFieldIsFarmer.getText();
		if (isFarmer.equals("Yes")) {
			data[2] = "1";
		} else {
			data[2] = "0";
		}
		data[3] = String.valueOf(passwordFieldRePassword.getPassword());
		return data;
	}

}
