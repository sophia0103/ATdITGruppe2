package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import controller.CancelController;
import controller.ChangePasswordDialogController;
import controller.Controller;
import gemuese4you.Starter;
import gemuese4you.Util;

/**
 * @author I518189 Represents the UI of the dialog which opens when the user
 *         wants to change the password.
 */
public class ChangePasswordDialogView extends JFrame implements DataView {
	private Container container;
	private JLabel labelOldPassword;
	private JLabel labelNewPassword;
	private JLabel labelNewPasswordRepeat;
	private JPasswordField passwordFieldOldPassword;
	private JPasswordField passwordFieldNewPassword;
	private JPasswordField passwordFieldNewPasswordRepeat;
	private JButton buttonSave;
	private JButton buttonCancel;
	private JPanel panelButtons;
	private JPanel panelInputFields;
	private Controller controller;
	private Controller cancelController;

	public ChangePasswordDialogView() {
		cancelController = new CancelController();

		container = getContentPane();
		container.setLayout(new BorderLayout());

		panelInputFields = new JPanel(new GridLayout(6, 1));

		labelOldPassword = new JLabel(Starter.content.getString("oldPassword"));
		passwordFieldOldPassword = new JPasswordField();
		labelNewPassword = new JLabel(Starter.content.getString("newPassword"));
		passwordFieldNewPassword = new JPasswordField();
		labelNewPasswordRepeat = new JLabel(Starter.content.getString("repeatPassword"));
		passwordFieldNewPasswordRepeat = new JPasswordField();

		panelInputFields.add(labelOldPassword);
		panelInputFields.add(passwordFieldOldPassword);
		panelInputFields.add(labelNewPassword);
		panelInputFields.add(passwordFieldNewPassword);
		panelInputFields.add(labelNewPasswordRepeat);
		panelInputFields.add(passwordFieldNewPasswordRepeat);

		panelInputFields.setBackground(Util.orange);

		panelButtons = new JPanel(new GridLayout(1, 2));

		buttonSave = new JButton(Starter.content.getString("save"));
		controller = new ChangePasswordDialogController();
		buttonSave.addActionListener(e -> controller.startProcess(this));

		buttonCancel = new JButton(Starter.content.getString("cancel"));
		buttonCancel.addActionListener(e -> cancelController.startProcess(this));

		panelButtons.add(buttonSave);
		panelButtons.add(buttonCancel);

		container.add(panelInputFields, BorderLayout.CENTER);
		container.add(panelButtons, BorderLayout.SOUTH);

		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle(Starter.content.getString("changePwTitle"));
	}

	@Override
	public String[] getData() {
		String[] inputArray = new String[3];
		inputArray[0] = String.valueOf(passwordFieldOldPassword.getPassword());
		inputArray[1] = String.valueOf(passwordFieldNewPassword.getPassword());
		inputArray[2] = String.valueOf(passwordFieldNewPasswordRepeat.getPassword());
		return inputArray;
	}

}