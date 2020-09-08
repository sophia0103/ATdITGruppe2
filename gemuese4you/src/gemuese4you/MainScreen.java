package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class MainScreen extends JFrame {
	private Container c;
	public static JTabbedPane tabPane;
	private JPanel pHome, pShop,  pJob, pProfile, pTitleBar;
	private JLabel lTitle;
	private static Connection connection;

	public MainScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.setBackground(Util.orange);

		tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
		pHome = new HomeScreen();
		pShop = new ShopScreen();
		pJob = new JobScreen();
		pProfile = new ProfileScreen();

		pShop.setBackground(Util.orange);
		pJob.setBackground(Util.orange);
		pProfile.setBackground(Util.orange);

		tabPane.addTab("Home", new ImageIcon("images/home.png"), pHome);
		tabPane.addTab("Shop", new ImageIcon("images/shop.png"), pShop);
		tabPane.addTab("Jobs", new ImageIcon("images/farmer.png"), pJob);
		tabPane.addTab("Profile", new ImageIcon("images/job.png"), pProfile);
		c.add(tabPane);

		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("Gemüse 4 You");
	}


}
