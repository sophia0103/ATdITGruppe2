package gemuese4you;
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


/**
 * @author Martin
 * Represents the frame which opens when a user wants to create an account
 */
public class Register extends JFrame implements ActionListener{
	JTextField tUsername, tIsFarmer;
	JPasswordField tPassword, tRePassword;
	JLabel lUsername, lPassword, lRePassword, lIsFarmer;
	JPanel reg, btn;
	JButton btCancel, btReg;
	Connection connection;

	public Register() {
			try {
				connection = Util.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		reg = new JPanel(new GridLayout(8, 1));
		btn = new JPanel (new GridLayout(1, 2));
		tUsername = new JTextField();
		tPassword = new JPasswordField();
		tRePassword = new JPasswordField();
		tIsFarmer = new JTextField();
		lUsername = new JLabel("Username: ");
		lPassword = new JLabel("Password: ");
		lRePassword = new JLabel("Confrim Password: ");
		lIsFarmer = new JLabel("Farmer? (Yes/No)");
		reg.add(lUsername);
		reg.add(tUsername);
		reg.add(lPassword);
		reg.add(tPassword);
		reg.add(lRePassword);
		reg.add(tRePassword);
		reg.add(lIsFarmer);
		reg.add(tIsFarmer);
		
		reg.setBackground(Util.orange);
		btn.setBackground(Util.orange);
		
		btCancel = new JButton("Cancel");
		btReg = new JButton("Confirm Registration");
		btn.add(btReg);
		btn.add(btCancel);
		
		c.add(reg, BorderLayout.CENTER);
		c.add(btn, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setTitle("Register");

		btCancel.addActionListener(this);
		btReg.addActionListener(this);
	}
	
	/**
	 * Checks if the username already exists in the users database table.
	 * @param username Username which is checked for.
	 * @return Returns true if the username exists in the database, otherwise false.
	 */
	public boolean checkUsername(String username) {
		
		PreparedStatement stmt;
		ResultSet rs;
		boolean checkUser = false;
		
		String query = "SELECT * FROM user WHERE UserID = ?";
		
		try {
			stmt = Util.getConnection().prepareStatement(query);
		stmt.setString(1, username);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			checkUser = true;
		}
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkUser;
	}
	
	
		/**
		 * Action which is performed when the user clicks the register button.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btReg) {
				String user = tUsername.getText();
				String pass = String.valueOf(tPassword.getPassword());
				String repass = String.valueOf(tRePassword.getPassword());
				String farmer = tIsFarmer.getText();
				String yesFarmer = "Yes";
				String nofarmer = "No";
				int isFarmer = 0;
				if(farmer.contentEquals(yesFarmer)) {
					isFarmer = 1;
				}else {
					isFarmer = 0;
				}
				
				if(user.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in a username");
				}else if(pass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in a password");
				}else if(!pass.equals(repass)) {
					JOptionPane.showMessageDialog(null, "Please retype your password");
				}else if(farmer.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill in, if you are a farmer(Yes) or not(No)");
				}else if(checkUsername(user)) {
					JOptionPane.showMessageDialog(null, "Username already exists!");
				}else {
				
				PreparedStatement stmt;
				String query = "INSERT INTO USER (UserID, Password, isFarmer) VALUES (?, ?, ?)";
				
				try {
					stmt = Util.getConnection().prepareStatement(query);
					stmt.setString(1, user);
					stmt.setString(2, pass);
					stmt.setString(3, String.valueOf(isFarmer));
					
					if(stmt.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "New User Added");
					}
					
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}else {
				this.dispose();
			}
			
		}
}
