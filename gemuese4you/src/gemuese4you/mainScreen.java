package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class mainScreen extends JFrame implements ActionListener {
	Container c;
	JTabbedPane tabPane;
	JPanel pHome, pShop, pJob, pProfile, pTitleBar;
	JLabel lTitle;
	Color orange;
	public static int offerID;
	public static ArrayList<Offer> offerList;
	private static Connection connection;

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

		tabPane.addTab("Home", new ImageIcon("images/home.png"), pHome);
		tabPane.addTab("Shop", new ImageIcon("images/shop.png"), pShop);
		tabPane.addTab("Jobs", new ImageIcon("images/farmer.png"), pJob);
		tabPane.addTab("Profile", new ImageIcon("images/job.png"), pProfile);
		c.add(tabPane);

		this.setVisible(true);
		this.setSize(500, 500);
		this.setTitle("Gemüse 4 You");
	}

	 public static Connection getConnection() throws ClassNotFoundException, SQLException {
         if (connection == null) {
             Class.forName("org.mariadb.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
         }
         return connection;
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

	public JPanel getShopScreen() {
		JPanel shopScreen = new JPanel(new BorderLayout());
		JPanel titlebar = getTitleBar("Shop");
		JButton btAddOffer = getAddOfferButton();
		btAddOffer.addActionListener(new AddOfferListener());
		titlebar.add(btAddOffer, BorderLayout.EAST);
		shopScreen.add(titlebar, BorderLayout.NORTH);
		offerList = new ArrayList<Offer>();
		JPanel pOffer = new JPanel(new GridLayout(offerList.size(), 1));
		for (int i = 0; i < offerList.size(); i++) {
//			pOffer.add(offerList.get(i));
		}
		shopScreen.add(pOffer, BorderLayout.CENTER);
		return shopScreen;
	}

	public JPanel newOffer(String name, int distance, String iconName) {
		offerID++;
		JPanel pOffer = new JPanel(new BorderLayout());
		JPanel pFooter = new JPanel();
		JLabel lDes = new JLabel(name + "´s offer is " + distance + " m away.");
		pFooter.add(lDes);
		pOffer.add(pFooter, BorderLayout.SOUTH);
		JButton btImage = new JButton();
		btImage.setIcon(new ImageIcon("images/" + iconName));
		btImage.setMargin(new Insets(0, 0, 0, 0));
		btImage.setBorder(null);
		pOffer.add(btImage, BorderLayout.CENTER);
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openAddOfferDialog(name, distance, iconName);
			}
		};
		btImage.addActionListener(al);
		return pOffer;
	}

	public void openAddOfferDialog(String name, int distance, String iconName) {
		JFrame openAddOfferDialog = new JFrame();
		openAddOfferDialog.setBackground(orange);
		Container c2 = openAddOfferDialog.getContentPane();
		c2.setBackground(orange);
		c2.setLayout(new GridLayout(3, 1));
		JLabel lOfferTitle = new JLabel(name + "´s offer: ");
		JLabel lDist = new JLabel("Distance: " + distance);
		JLabel lProducts = new JLabel("Products included: "+ iconName.substring(0, iconName.length()-4));
		c2.add(lOfferTitle);
		c2.add(lDist);
		c2.add(lProducts);
		openAddOfferDialog.setVisible(true);
		openAddOfferDialog.setSize(500, 500);
		
		
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
	
	public JButton getAddOfferButton() {
		JButton btAdd = new JButton();
		btAdd = new JButton();
		btAdd.setBackground(orange);
		btAdd.setIcon(new ImageIcon("images/add.png"));
		btAdd.setMargin(new Insets(0, 0, 0, 0));
		btAdd.setBorder(null);
		btAdd.addActionListener(this);
		return btAdd;
	}

	class AddOfferListener implements ActionListener {
		

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Statement stmt = connection.createStatement();
			String queryOffers = "SELECT * FROM offers";
			ResultSet resOffers = stmt.executeQuery(queryOffers);
			while(!resOffers.isAfterLast()) {
				String queryProductList = "SELECT product FROM productsinoffer WHERE productsinoffer.offerID = "+resOffers.getInt(1);
//				offerList.add(new Offer(resOffers.getInt(1), resOffers.getInt(2), resOffers.getInt(3)))
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	}
}
