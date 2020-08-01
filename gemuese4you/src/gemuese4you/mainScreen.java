package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener {
Container c;
JTabbedPane tabPane;
JPanel pHome, pShop, pJob, pProfile, pTitleBar;
JLabel lTitle;
Color orange;

	public mainScreen() {
		c = getContentPane();
		c.setLayout(new BorderLayout());
		orange = new Color(255, 229, 204);
		c.setBackground(orange);
		
		tabPane = new JTabbedPane(JTabbedPane.BOTTOM);
		pHome = getHomeScreen();
		pShop = getShopScreen();
		
		pShop.setBackground(orange);
		pJob = new JPanel();
		pJob.setBackground(orange);
		pProfile = new JPanel();
		pProfile.setBackground(orange);
		
		tabPane.addTab("Home",new ImageIcon("images/home.png"), pHome);
		tabPane.addTab("Shop",new ImageIcon("images/shop.png"), pShop);
		tabPane.addTab("Jobs",new ImageIcon("images/farmer.png"), pJob);
		tabPane.addTab("Profile",new ImageIcon("images/job.png"), pProfile);
		c.add(tabPane);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Gemüse 4 You");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		loginScreen log = new loginScreen();
//		log.setLocation(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
		log.setVisible(true);
		log.setSize(500, 500);
		log.setTitle("Gemüse 4 You");
	}

	
	public JPanel getHomeScreen() {
		JPanel homeScreen = new JPanel(new BorderLayout());
		homeScreen.setBackground(orange);
		JPanel titlebar = getTitleBar("Home");
		homeScreen.add(titlebar, BorderLayout.NORTH);
		return homeScreen;
	}
	
	public JPanel getShopScreen(){
		JPanel shopScreen = new JPanel(new BorderLayout());
		JPanel titlebar = getTitleBar("Shop");
		shopScreen.add(titlebar, BorderLayout.NORTH);
		return shopScreen;
	}
	
	public JPanel getTitleBar(String title) {
		JPanel pTitleBar = new JPanel(new BorderLayout());
		JLabel lTitle = new JLabel(title);
		lTitle.setFont(new Font("Verdana", Font.BOLD, 20));
		pTitleBar.add(lTitle, BorderLayout.CENTER);
		pTitleBar.setBackground(orange);
		pTitleBar.add(getBackButton(), BorderLayout.WEST);
		return pTitleBar;
	}
	
	public JButton getBackButton() {
		JButton btBack = new JButton();
		btBack = new JButton();
		btBack.setBackground(orange);
		btBack.setIcon(new ImageIcon("images/back2.png"));
		btBack.setMargin(new Insets(0, 0, 0, 0));
		btBack.setBorder(null);
		btBack.addActionListener(this);
		return btBack;
	}
	
}
