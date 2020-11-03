package view;

import java.awt.*;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import controller.RegisterCancelController;
import controller.RegisterController;
import gemuese4you.Starter;
import gemuese4you.Util;

public class RegisterView extends JFrame implements DataView{

	
	//declaring variables
	Container container;
	static Connection connection;

	private JTextField textFieldUser;
	private JPasswordField passwordFieldPassword, passwordFieldRePassword;
	private JLabel labelUser, labelPassword, labelRePassword, labelIsFarmer;
	private JPanel panelRegister, panelButton;
	private JCheckBox checkBoxIsFarmer;
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
		checkBoxIsFarmer = new JCheckBox();
		labelUser = new JLabel(Starter.content.getString("username"));
		labelPassword = new JLabel(Starter.content.getString("password"));
		labelRePassword = new JLabel(Starter.content.getString("confirmPassword"));
		labelIsFarmer = new JLabel(Starter.content.getString("farmerQuestion"));

		panelRegister.add(labelUser);
		panelRegister.add(textFieldUser);
		panelRegister.add(labelPassword);
		panelRegister.add(passwordFieldPassword);
		panelRegister.add(labelRePassword);
		panelRegister.add(passwordFieldRePassword);
		panelRegister.add(labelIsFarmer);
		panelRegister.add(checkBoxIsFarmer);

		panelRegister.setBackground(Util.orange);
		panelButton.setBackground(Util.orange);
		
		registerCancelController = new RegisterCancelController();
		
		buttonRegister = new JButton(Starter.content.getString("confirmRegistration"));
		registerController = new RegisterController();
		buttonRegister.addActionListener(e -> registerController.startProcess(this));
		buttonCancel = new JButton(Starter.content.getString("cancel"));
		registerCancelController = new RegisterCancelController();
		buttonCancel.addActionListener(e -> registerCancelController.startProcess(this));
		
		panelButton.add(buttonRegister);
		panelButton.add(buttonCancel);



		container.add(panelRegister, BorderLayout.CENTER);
		container.add(panelButton, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle(Starter.content.getString("register"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	//give getter methods for easy access to text in textfields
	
	public JTextField getTextFieldUser() {
		return textFieldUser;
	}

	public JCheckBox getCheckBoxFieldIsFarmer() {
		return checkBoxIsFarmer;
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
		if (checkBoxIsFarmer.isSelected()) 
			data[3] = "1";
		else
			data[3] = "0";
		
		data[2] = String.valueOf(passwordFieldRePassword.getPassword());
		return data;
	}

}
