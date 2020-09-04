package gemuese4you;

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
import javax.swing.JPasswordField;

public class ChangePasswordDialog extends JFrame implements ActionListener{
	private Container container;
	private Connection connection;
	private JLabel labelOldPassword, labelNewPassword, labelNewPasswordRepeat;
	private JPasswordField passwordFieldOldPassword, passwordFieldNewPassword, passwordFieldNewPasswordRepeat;
	private JButton buttonSave;

	public ChangePasswordDialog() {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		container = getContentPane();
		container.setLayout(new GridLayout(7, 1));
		labelOldPassword = new JLabel("Enter your old password: ");
		passwordFieldOldPassword = new JPasswordField();
		labelNewPassword = new JLabel("Enter your new password: ");
		passwordFieldNewPassword = new JPasswordField();
		labelNewPasswordRepeat = new JLabel("Repeat your new password: ");
		passwordFieldNewPasswordRepeat = new JPasswordField();
		buttonSave = Util.getCustomButton("save");
		buttonSave.addActionListener(this);
		container.add(labelOldPassword);
		container.add(passwordFieldOldPassword);
		container.add(labelNewPassword);
		container.add(passwordFieldNewPassword);
		container.add(labelNewPasswordRepeat);
		container.add(passwordFieldNewPasswordRepeat);
		container.add(buttonSave);
		container.setBackground(Util.orange);
		this.setVisible(true);
		this.setSize(500, 500);
	}


	//Change the password in the user database table
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String newPassword = String.valueOf(passwordFieldNewPassword.getPassword());
			String newPasswordRepeat = String.valueOf(passwordFieldNewPasswordRepeat.getPassword());
			if (!newPassword.equals(newPasswordRepeat)) {
				throw new Exception();
			}
			Statement statement = connection.createStatement();
			String queryChangePassword = "UPDATE user SET password = '" + newPasswordRepeat + "' WHERE userID ='"+LoginScreen.userID+"'";
			statement.execute(queryChangePassword);
			JOptionPane.showMessageDialog(null, "Password changed.");
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Please enter the same values for the new password.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
			this.dispose();
		}

	}

}