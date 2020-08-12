package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class mainScreen extends JFrame {
	Container c;
	JTabbedPane tabPane;
	JPanel pHome, pShop,  pJob, pProfile, pTitleBar;
	JLabel lTitle;
	Color orange;
	private static Connection connection;

	public mainScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		orange = new Color(255, 229, 204);
		c.setBackground(orange);

		tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
		pHome = getHomeScreen();
		pShop = new shopScreen();

		pShop.setBackground(orange);
		pJob = new JPanel();
		pJob.setBackground(orange);
		pProfile = new JPanel();
		pProfile.setBackground(orange);

		tabPane.addTab("Home", new ImageIcon("images/home.png"), pHome);
		tabPane.addTab("Shop", new ImageIcon("images/shop.png"), pShop);
		tabPane.addTab("Jobs", new ImageIcon("images/farmer.png"), pJob);
		tabPane.addTab("Profile", new ImageIcon("images/job.png"), pProfile);
		c.add(tabPane);

		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Gemüse 4 You");
	}

	public JPanel getHomeScreen() {
		JPanel homeScreen = new JPanel(new BorderLayout());
		homeScreen.setBackground(orange);
//		JPanel titlebar = getTitleBar("Home");
//		homeScreen.add(titlebar, BorderLayout.NORTH);

		JPanel homeScreenContent = new JPanel();
		homeScreenContent.setLayout(new BoxLayout(homeScreenContent, BoxLayout.Y_AXIS));
		homeScreenContent.setBackground(orange);

		JLabel headlineNewestJobs = new JLabel("Neueste Stellenangebote:");
		headlineNewestJobs.setFont(new Font("Arial", Font.BOLD, 16));
		headlineNewestJobs.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 30));
		JButton newestJobs = new JButton();
		newestJobs.setLayout(new BoxLayout(newestJobs, BoxLayout.Y_AXIS));
		newestJobs.setFocusPainted(false);
		newestJobs.setContentAreaFilled(false);
		JLabel row1 = new JLabel("\n\u2022 Erntehelfer/in Ludwigshafen");
		JLabel row2 = new JLabel("\n\u2022 Ernteleiter/in Kaiserslautern");
		JLabel row3 = new JLabel("\n\u2022 Kassierer/in Hofladen Jürgens in Walldorf");
		newestJobs.add(row1);
		newestJobs.add(row2);
		newestJobs.add(row3);
		newestJobs.setBackground(new Color(255, 242, 229));
		newestJobs.setOpaque(true);

		JLabel headlineNewestJobs1 = new JLabel("Neueste Stellenangebote:");
		headlineNewestJobs1.setFont(new Font("Arial", Font.BOLD, 16));
		headlineNewestJobs1.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 30));
		JButton newestJobs1 = new JButton();
		newestJobs1.setSize(300, 600);
		newestJobs1.setLayout(new BoxLayout(newestJobs1, BoxLayout.Y_AXIS));
		newestJobs1.setFocusPainted(false);
		newestJobs1.setContentAreaFilled(false);
		JLabel row11 = new JLabel("\n\u2022 Erntehelfer/in Ludwigshafen");
		JLabel row21 = new JLabel("\n\u2022 Ernteleiter/in Kaiserslautern");
		JLabel row31 = new JLabel("\n\u2022 Kassierer/in Hofladen Jürgens in Walldorf");
		newestJobs1.add(row11);
		newestJobs1.add(row21);
		newestJobs1.add(row31);
		newestJobs1.setBackground(new Color(255, 242, 229));
		newestJobs1.setOpaque(true);

		homeScreenContent.add(headlineNewestJobs);
		homeScreenContent.add(newestJobs);
		homeScreenContent.add(headlineNewestJobs1);
		homeScreenContent.add(newestJobs1);

		homeScreenContent.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));

		JScrollPane scrollable = new JScrollPane(homeScreenContent, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollable.setBorder(BorderFactory.createEmptyBorder());
		homeScreen.add(scrollable, BorderLayout.CENTER);
		return homeScreen;
	}


}
