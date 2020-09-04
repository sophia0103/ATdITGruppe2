package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class LoginScreen extends JFrame implements ActionListener{
	
	 private static Util connection;
	 public static String userID;

	MainScreen ms;
    Container c;
    JPanel pLogin, pButtons;
    JLabel lUser, lPassword, lLoginTitle, lEmpty, lTitle;
    JButton btLogin, btRegister;
    JPasswordField tPassword;
    JTextField tUser;

	public LoginScreen() {
		connection = new Util();
		if(connection == null) {
			JOptionPane.showMessageDialog(this, "No connection to data base available!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		c = getContentPane();
		c.setLayout(new BorderLayout());
		Color orange = new Color(255, 229, 204);
		c.setBackground(orange);
		
		pLogin = new JPanel(new GridLayout(3, 2));
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
		lLoginTitle = new JLabel("Gemüse4You");
		lLoginTitle.setFont(new Font("Verdana", Font.ITALIC, 18));
		lLoginTitle.setIcon(new ImageIcon("images/carrot.png"));
		
		pLogin.add(lLoginTitle);
		pLogin.add(lEmpty);
		pLogin.add(lUser);
		pLogin.add(tUser);
		pLogin.add(lPassword);
		pLogin.add(tPassword);
		
		pButtons.add(btLogin);
		pButtons.add(btRegister);
		
		c.add(lTitle, BorderLayout.NORTH);
		c.add(pLogin, BorderLayout.CENTER);
		c.add(pButtons, BorderLayout.SOUTH);
		
	}


	public static void main(String[] args) {
		LoginScreen log = new LoginScreen();
		log.setVisible(true);
		log.setSize(500, 500);
		log.setTitle("Gemüse 4 You");
		log.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
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