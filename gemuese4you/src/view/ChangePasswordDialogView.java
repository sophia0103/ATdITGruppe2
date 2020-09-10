package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.ChangePasswordDialogController;
import gemuese4you.Util;

public class ChangePasswordDialogView extends JFrame{
	private Container container;
	private Connection connection;
	private JLabel labelOldPassword, labelNewPassword, labelNewPasswordRepeat;
	private JPasswordField passwordFieldOldPassword, passwordFieldNewPassword, passwordFieldNewPasswordRepeat;
	private JButton buttonSave, buttonCancel;
	private JPanel panelButtons, panelInputFields;
	private ChangePasswordDialogController changePasswordDialogController;
	
	public ChangePasswordDialogView() {
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		
		
		panelInputFields = new JPanel(new GridLayout(6, 1));
		
		labelOldPassword = new JLabel("Enter your old password: ");
		passwordFieldOldPassword = new JPasswordField();
		labelNewPassword = new JLabel("Enter your new password: ");
		passwordFieldNewPassword = new JPasswordField();
		labelNewPasswordRepeat = new JLabel("Repeat your new password: ");
		passwordFieldNewPasswordRepeat = new JPasswordField();
		
		panelInputFields.add(labelOldPassword);
		panelInputFields.add(passwordFieldOldPassword);
		panelInputFields.add(labelNewPassword);
		panelInputFields.add(passwordFieldNewPassword);
		panelInputFields.add(labelNewPasswordRepeat);
		panelInputFields.add(passwordFieldNewPasswordRepeat);
		
		panelInputFields.setBackground(Util.orange);
		
		panelButtons = new JPanel(new GridLayout(1,2));
		
		changePasswordDialogController = new ChangePasswordDialogController(this);

		buttonSave = new JButton("Save");
		buttonSave.addActionListener(changePasswordDialogController.getSaveListener());
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(changePasswordDialogController.getCancelListener());
		
		panelButtons.add(buttonSave);
		panelButtons.add(buttonCancel);
		
		container.add(panelInputFields, BorderLayout.CENTER);
		container.add(panelButtons, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Change your Password");
	}

	public JPasswordField getPasswordFieldOldPassword() {
		return passwordFieldOldPassword;
	}

	public JPasswordField getPasswordFieldNewPassword() {
		return passwordFieldNewPassword;
	}

	public JPasswordField getPasswordFieldNewPasswordRepeat() {
		return passwordFieldNewPasswordRepeat;
	}


}