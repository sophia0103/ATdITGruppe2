package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class loginScreen extends JFrame implements ActionListener{
Container c;
JPanel pLogin;
JLabel lUser, lPassword, lLoginTitle, lEmpty, lTitle;
JButton btLogin;
JPasswordField tPassword;
JTextField tUser;

	public loginScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		pLogin = new JPanel(new GridLayout(3, 2));
		
		lUser = new JLabel("Username: ");
		lPassword = new JLabel("Password: ");
		lTitle = new JLabel("Log-In");
		lEmpty = new JLabel("");
		
		tUser = new JTextField();
		tPassword = new JPasswordField();
		
		btLogin = new JButton("Log-In");
		btLogin.addActionListener(this);
		lLoginTitle = new JLabel("Gemüse4You");
		
		pLogin.add(lLoginTitle);
		pLogin.add(lEmpty);
		pLogin.add(lUser);
		pLogin.add(tUser);
		pLogin.add(lPassword);
		pLogin.add(tPassword);
		
		c.add(lTitle, BorderLayout.NORTH);
		c.add(pLogin, BorderLayout.CENTER);
		c.add(btLogin, BorderLayout.SOUTH);
		
	}


	public static void main(String[] args) {
		loginScreen log = new loginScreen();
		log.setVisible(true);
		log.setSize(500, 500);
		log.setTitle("Gemüse 4 You");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		mainScreen ms = new mainScreen();
		
	}

}
