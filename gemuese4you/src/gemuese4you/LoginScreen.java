package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


/**
 * @author Martin
 * Represents the Login Screen in which the user can either login or register him-/herself.
 */
public class LoginScreen extends JFrame implements ActionListener{
	
	 private Connection connection;
	 public static String userID;

	MainScreen ms;
    Container c;
    JPanel pLogin, pButtons, pFrame;
    JLabel lUser, lPassword, lLoginTitle, lEmpty, lTitle;
    JButton btLogin, btRegister;
    JPasswordField tPassword;
    JTextField tUser;

	public LoginScreen() {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		if(connection == null) {
//			JOptionPane.showMessageDialog(this, "No connection to data base available!", "Error", JOptionPane.ERROR_MESSAGE);
//		}
		c = getContentPane();
		c.setLayout(new BorderLayout());
		Color orange = new Color(255, 229, 204);
		c.setBackground(orange);
		
		pFrame = new JPanel();
		pFrame.setBackground(Util.orange);
		
		pLogin = new JPanel(new GridLayout(2, 2));
		pLogin.setBackground(orange);
		
		pButtons = new JPanel(new GridLayout(1, 2));
		pButtons.setBackground(orange);
		
		lUser = new JLabel("Username: ");
		lPassword = new JLabel("Password: ");
		
		lTitle = new JLabel("Log-In");
		lTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		lEmpty = new JLabel("");
		
		tUser = new JTextField();
		tUser.setPreferredSize(new Dimension(200, 50));
		
		tPassword = new JPasswordField();
		
		btLogin = new JButton("Log-In");
		btLogin.addActionListener(this);
		btRegister = new JButton("Register");
		btRegister.addActionListener(this);
		
		pLogin.add(lUser);
		pLogin.add(tUser);
		pLogin.add(lPassword);
		pLogin.add(tPassword);
		
		pButtons.add(btLogin);
		pButtons.add(btRegister);
		
		pFrame.add(pLogin);
		pFrame.add(pButtons);
		
		c.add(lTitle, BorderLayout.NORTH);
		c.add(pFrame, BorderLayout.CENTER);
		c.add(pButtons, BorderLayout.SOUTH);
		
		ImageIcon frameIcon = new ImageIcon("images/carrotMain.png");
		this.setIconImage(frameIcon.getImage());
		
	}


	public static void main(String[] args) {
		LoginScreen log = new LoginScreen();
		log.setVisible(true);
		log.pack();
		log.setLocationRelativeTo(null);
		log.setTitle("Gemüse 4 You");
		log.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	/**
	 * Action which is performed when the user clicks the login or register button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRegister) {
			Register register = new Register();
		}else {
			
			PreparedStatement stmt;
			ResultSet rs;
			String user = tUser.getText();
			String pass = String.valueOf(tPassword.getPassword());
			
			String query = "SELECT * FROM user WHERE UserID = ? AND Password = ?";
			
			try {
				stmt = Util.getConnection().prepareStatement(query);
				
				stmt.setString(1, user);
				stmt.setString(2, pass);
				
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "Welcome");
					this.userID = user;
					ms = new MainScreen();
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "Username or password wrong!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}