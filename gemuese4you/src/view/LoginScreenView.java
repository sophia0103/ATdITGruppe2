package view;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import controller.LoginScreenController;
import gemuese4you.MainScreen;
import gemuese4you.Util;

public class LoginScreenView extends JFrame {

	
	//declaring variables
	static Connection connection;
	public static String userID;

	Container container;
	private JPanel panelLogin, panelButtons, panelFrame;
	private JLabel labelUser, labelPassword, labelLoginTitle, labelEmpty, labelTitle;
	private JButton buttonLogin, buttonRegister;
	private JPasswordField passwordFieldPassword;
	private JTextField textFieldUser;
	private LoginScreenController loginScreenController;

	// constructor to create a Screen with below components
	public LoginScreenView() {

		container = getContentPane();
		container.setLayout(new BorderLayout());
		Color orange = new Color(255, 229, 204);
		container.setBackground(orange);

		panelFrame = new JPanel();
		panelFrame.setBackground(Util.orange);

		panelLogin = new JPanel(new GridLayout(2, 2));
		panelLogin.setBackground(orange);

		panelButtons = new JPanel(new GridLayout(1, 2));
		panelButtons.setBackground(orange);

		labelUser = new JLabel("Username: ");
		labelPassword = new JLabel("Password: ");

		labelTitle = new JLabel("Log-In");
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelEmpty = new JLabel("");

		textFieldUser = new JTextField();
		textFieldUser.setPreferredSize(new Dimension(200, 50));

		passwordFieldPassword = new JPasswordField();

		loginScreenController = new LoginScreenController(this);

		buttonLogin = new JButton("Log-In");
		buttonLogin.addActionListener(loginScreenController.getLoginListener());
		buttonRegister = new JButton("Register");
		buttonRegister.addActionListener(loginScreenController.getRegisterListener());

		panelLogin.add(labelUser);
		panelLogin.add(textFieldUser);
		panelLogin.add(labelPassword);
		panelLogin.add(passwordFieldPassword);

		panelButtons.add(buttonLogin);
		panelButtons.add(buttonRegister);

		panelFrame.add(panelLogin);
		panelFrame.add(panelButtons);

		container.add(labelTitle, BorderLayout.NORTH);
		container.add(panelFrame, BorderLayout.CENTER);
		container.add(panelButtons, BorderLayout.SOUTH);

		ImageIcon frameIcon = new ImageIcon("images/carrotMain.png");

		this.setIconImage(frameIcon.getImage());
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Gemüse 4 You");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public JPasswordField getPasswordFieldPassword() {
		return passwordFieldPassword;
	}

	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	// start of the program
	public static void main(String[] args) {
		LoginScreenView log = new LoginScreenView();

	}

}