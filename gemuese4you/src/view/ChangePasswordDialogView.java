package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.ChangePasswordDialogController;
import controller.Controller;
import gemuese4you.Util;
import model.ChangePasswordCredentials;

/**
 * @author I518189
 * Represents the UI of the dialog which opens when the user wants to change the password.
 */
public class ChangePasswordDialogView extends JFrame implements DataView{
	private Container container;
	private JLabel labelOldPassword, labelNewPassword, labelNewPasswordRepeat;
	private JPasswordField passwordFieldOldPassword, passwordFieldNewPassword, passwordFieldNewPasswordRepeat;
	private JButton buttonSave, buttonCancel;
	private JPanel panelButtons, panelInputFields;
	private ChangePasswordDialogController controller;

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

		panelButtons = new JPanel(new GridLayout(1, 2));

		controller = new ChangePasswordDialogController();

		buttonSave = new JButton("Save");
		
		buttonSave.addActionListener(e -> this.controller.startProcess(this));

		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(e -> this.dispose());

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


	@Override
	public <T> T getData() {
		String oldPassword = String.valueOf(passwordFieldOldPassword.getPassword());
		String newPassword = String.valueOf(passwordFieldNewPassword.getPassword());
		String newPasswordRepeat = String.valueOf(passwordFieldNewPasswordRepeat.getPassword());
		ChangePasswordCredentials changePasswordCredentials = new ChangePasswordCredentials(oldPassword, newPassword, newPasswordRepeat);
		return (T) changePasswordCredentials;
	}

	
}