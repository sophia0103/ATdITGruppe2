package view;

import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

import controller.Controller;
import controller.LoginController;
import controller.OpenRegisterViewController;
import gemuese4you.Starter;
import gemuese4you.Util;
/**
 * @author Martin
 * Represents the UI of the login screen.
 */
public class LoginScreenView extends JFrame implements DataView{

	
	//declaring variables
	static Connection connection;
	public static String userID;

	Container container;
	private JPanel panelLogin, panelButtons, panelFrame;
	private JLabel labelUser, labelPassword, labelLoginTitle, labelEmpty, labelTitle;
	private JButton buttonLogin, buttonRegister;
	private JPasswordField passwordFieldPassword;
	private JTextField textFieldUser;
	private Controller openRegisterViewController;
	private Controller loginController;

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

		labelUser = new JLabel(Starter.content.getString("username"));
		labelPassword = new JLabel(Starter.content.getString("password"));

		labelTitle = new JLabel(Starter.content.getString("login"));
		labelTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		labelEmpty = new JLabel("");

		textFieldUser = new JTextField();
		textFieldUser.setPreferredSize(new Dimension(200, 50));

		passwordFieldPassword = new JPasswordField();

		buttonLogin = new JButton(Starter.content.getString("login"));
		loginController = new LoginController();
		buttonLogin.addActionListener(e -> loginController.startProcess(this));
		buttonRegister = new JButton(Starter.content.getString("register"));
		openRegisterViewController = new OpenRegisterViewController();
		buttonRegister.addActionListener(e -> openRegisterViewController.startProcess(this));

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

		ImageIcon frameIcon = new ImageIcon("src/main/resources/carrotMain.png");

		this.setIconImage(frameIcon.getImage());
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setTitle("Gemüse 4 You");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@Override
	public String[] getData() {
		String[] data = new String[2];
		data[0] = textFieldUser.getText();
		data[1] = String.valueOf(passwordFieldPassword.getPassword());
		return data;
	}


}