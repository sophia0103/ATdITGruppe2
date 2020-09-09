package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ChangePasswordDialog extends JFrame implements ActionListener {
	private Container container;
	private Connection connection;
	private JLabel labelOldPassword, labelNewPassword, labelNewPasswordRepeat;
	private JPasswordField passwordFieldOldPassword, passwordFieldNewPassword, passwordFieldNewPasswordRepeat;
	private JButton buttonSave, buttonCancel;
	private JPanel panelButtons, panelInputFields;

	public ChangePasswordDialog() {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(this);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(this);
		
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

	// Change the password in the user database table
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonSave) {
		try {
			String oldPassword = String.valueOf(passwordFieldOldPassword.getPassword());
			String newPassword = String.valueOf(passwordFieldNewPassword.getPassword());
			String newPasswordRepeat = String.valueOf(passwordFieldNewPasswordRepeat.getPassword());

			if (inputIsValid(oldPassword, newPassword, newPasswordRepeat)) {
				Statement statement = connection.createStatement();
				String queryChangePassword = "UPDATE user SET password = '" + newPasswordRepeat + "' WHERE userID ='"
						+ LoginScreen.userID + "'";
				statement.execute(queryChangePassword);
				JOptionPane.showMessageDialog(null, "Password changed.");
				this.dispose();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		}
		else {
			this.dispose();
		}
	}

	public boolean inputIsValid(String oldPassword, String newPassword, String newPasswordRepeat) {
		if(oldPassword.equals("") || newPassword.equals("") || newPasswordRepeat.equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!newPassword.equals(newPasswordRepeat)) {
			JOptionPane.showMessageDialog(null, "Please enter the same values for the new password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!oldPassword.equals(Util.getPassword())) {
			JOptionPane.showMessageDialog(null, "Old password is not correct.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(oldPassword.equals(newPasswordRepeat)) {
			JOptionPane.showMessageDialog(null, "New password can´t be the same as the old password.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}