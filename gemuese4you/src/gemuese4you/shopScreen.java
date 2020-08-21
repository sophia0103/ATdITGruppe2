package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class shopScreen extends Screen {
	public static ArrayList<Offer> offerList;
	static Connection connection;
	public static int lastOfferID;
	JPanel offerPanel;

	public shopScreen() {
		this.setLayout(new BorderLayout());
		JPanel titlebar = getTitleBar("Shop");
		JButton btAddOffer = getAddOfferButton();
		JButton btRefresh = getRefreshButton();
		JPanel pEast = new JPanel(new GridLayout(1, 2));
		pEast.add(btRefresh);
		pEast.add(btAddOffer);
		titlebar.add(pEast, BorderLayout.EAST);
		this.add(titlebar, BorderLayout.NORTH);

		offerList = new ArrayList<Offer>();

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showCurrentOffers();
	}

	public static int getLastOfferID() {
		int lastOfferID;
		try {
			Statement stmt = connection.createStatement();
			;
			String lastOfferIDQuery = "SELECT COUNT(offerID) FROM offers";
			ResultSet rsLast = stmt.executeQuery(lastOfferIDQuery);
			rsLast.next();
			lastOfferID = rsLast.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Integer) null;
		}
		return lastOfferID;
	}

	// reads the current offers in the database and adds them to the offer list
	public void readOffers() {
		Statement stmt;
		offerList.clear();
		try {
			stmt = connection.createStatement();
			String queryOffers = "SELECT * FROM offers";
			ResultSet resOffers = stmt.executeQuery(queryOffers);
			while (!resOffers.isAfterLast()) {
				if (resOffers.next()) {
					int offerID = resOffers.getInt(1);
					String userID = resOffers.getString(2);
					int distance = resOffers.getInt(3);
					String date = resOffers.getString(4);
					offerList.add(new Offer(offerID, userID, distance, date));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returns a panel for one offer in order to display them on the shopScreen
	public JPanel getOfferPanel(Offer offer) {
		JPanel pOffer = new JPanel(new BorderLayout());
		JLabel pFooter = new JLabel(
				offer.getDate() + " : " + offer.getUserID() + "´s offer is " + offer.getDistance() + " m away.");
		JButton btIcon = new JButton();
		btIcon.setIcon(new ImageIcon("images/" + offer.getProductList().get(0) + ".png"));
		btIcon.setMargin(new Insets(0, 0, 0, 0));
		btIcon.setBorder(null);
		ActionListener offerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getOfferDialog(offer);
			}
		};
		btIcon.addActionListener(offerListener);
		pOffer.add(pFooter, BorderLayout.SOUTH);
		pOffer.add(btIcon, BorderLayout.CENTER);
		return pOffer;
	}

	// opens a dialog in which the attributes of the specified offer are listed
	public void getOfferDialog(Offer offer) {
		JFrame openAddOfferDialog = new JFrame();
		openAddOfferDialog.setBackground(orange);
		Container c = openAddOfferDialog.getContentPane();
		c.setBackground(orange);
		c.setLayout(new GridLayout(3, 1));
		JLabel lOfferTitle = new JLabel(offer.getUserID() + "´s offer: ");
		JLabel lDist = new JLabel("Distance: " + offer.getDistance());
		String prod = "";
		for (int i = 0; i < offer.getProductList().size(); i++) {
			prod = prod + offer.getProductList().get(i) + ", ";
		}
		JLabel lProducts = new JLabel("Products included: " + prod.substring(0, prod.length() - 2));
		c.add(lOfferTitle);
		c.add(lDist);
		c.add(lProducts);
		openAddOfferDialog.setVisible(true);
		openAddOfferDialog.setSize(500, 500);

	}

	// returns a button to add an offer
	public JButton getAddOfferButton() {
		JButton btAdd = new JButton();
		btAdd = new JButton();
		btAdd.setBackground(orange);
		btAdd.setIcon(new ImageIcon("images/add.png"));
		btAdd.setMargin(new Insets(0, 0, 0, 0));
		btAdd.setBorder(null);
		// Function which is called when the add offer button is pressed
		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddOfferScreen AddOfferDialog = new AddOfferScreen();
			}
		};
		btAdd.addActionListener(addListener);
		return btAdd;
	}

	public JButton getRefreshButton() {
		JButton btRefresh = new JButton();
		btRefresh = new JButton();
		btRefresh.setBackground(orange);
		btRefresh.setIcon(new ImageIcon("images/refresh.png"));
		btRefresh.setMargin(new Insets(0, 0, 0, 0));
		btRefresh.setBorder(null);
		ActionListener refreshListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCurrentOffers();
			}
		};
		btRefresh.addActionListener(refreshListener);
		return btRefresh;
	}

	public void showCurrentOffers() {
		// display existing offers
		if(offerPanel!=null) {
			this.remove(offerPanel);
			offerPanel=null;
		}
		readOffers();
		lastOfferID = getLastOfferID();
		offerPanel = new JPanel(new GridLayout(offerList.size(), 1));
		for (int i = 0; i < offerList.size(); i++) {
			offerPanel.add(getOfferPanel(offerList.get(i)));
		}
		this.add(offerPanel, BorderLayout.CENTER);
	}

}
