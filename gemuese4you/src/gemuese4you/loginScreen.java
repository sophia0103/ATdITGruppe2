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
		Color orange = new Color(255, 229, 204);
		c.setBackground(orange);
		
		pLogin = new JPanel(new GridLayout(3, 2));
		pLogin.setBackground(orange);
		
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
		lLoginTitle = new JLabel("Gemüse4You");
		lLoginTitle.setFont(new Font("Verdana", Font.ITALIC, 18));
		lLoginTitle.setIcon(new ImageIcon("images/carrot.png"));
		
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
