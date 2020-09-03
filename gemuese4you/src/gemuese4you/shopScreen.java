package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class shopScreen extends Screen {
	private static ArrayList<Offer> offerList;
	private static Connection connection;
	private JPanel pOffer, pEast,pTitlebar;
	private JButton btAddOffer, btRefresh;
	public static int lastOfferID;

	public shopScreen() {
		this.setLayout(new BorderLayout());
	 pTitlebar = getTitleBar("Shop");
		btAddOffer = getAddOfferButton();
		btRefresh = getRefreshButton();
		pEast = new JPanel(new GridLayout(1, 2));
		pEast.add(btRefresh);
		pEast.add(btAddOffer);
		pTitlebar.add(pEast, BorderLayout.EAST);
		this.add(pTitlebar, BorderLayout.NORTH);

		offerList = new ArrayList<Offer>();

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		showCurrentOffers();
	}

	// display existing offers
	public void showCurrentOffers() {
		// panel has to be removed and added again in order to fetch new offers which
		// might have been created in the process
		if (pOffer != null) {
			this.remove(pOffer);
			pOffer = null;
		}
		readOffers();
		pOffer = new JPanel(new GridLayout(offerList.size(), 1));
		for (int i = 0; i < offerList.size(); i++) {
			pOffer.add(getOfferPanel(offerList.get(i)));
		}
		this.add(pOffer, BorderLayout.CENTER);
	}

	// reads the current offers in the database and adds them to the offer list
	public void readOffers() {
		offerList.clear();
		try {
			String today = Util.returnDateAsString();
			Statement stmt = connection.createStatement();
			String queryOffers = "SELECT * FROM offers WHERE exp_date > '"+ today +"' ORDER BY distance";
			ResultSet resOffers = stmt.executeQuery(queryOffers);
			while (!resOffers.isAfterLast() && Util.checkDatabaseEntries("offerID", "offers")) {
				if (resOffers.next()) {
					int offerID = resOffers.getInt(1);
					String userID = resOffers.getString(2);
					int distance = resOffers.getInt(3);
					String expDate = resOffers.getString(4);
					double price = resOffers.getDouble(5);
					offerList.add(new Offer(offerID, userID, distance, expDate, price));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// returns a panel for each offer in order to display them on the shopScreen
	public JPanel getOfferPanel(Offer offer) {
		JPanel pOffer = new JPanel(new BorderLayout());
		JLabel pFooter = new JLabel(offer.getUserID() + "´s offer is " + offer.getDistance() + " m away.");
		pOffer.setBackground(new Color(255,237,203));
		JButton btIcon = Util.getCustomButton(offer.getProductList().get(0));
		ActionListener offerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GetOfferDialog(offer);
			}
		};
		btIcon.addActionListener(offerListener);
		pOffer.add(pFooter, BorderLayout.SOUTH);
		pOffer.add(btIcon, BorderLayout.CENTER);
		return pOffer;
	}

	// returns a button to add an offer
	public JButton getAddOfferButton() {
		JButton btAdd = Util.getCustomButton("add");
		// Function which is called when the add offer button is pressed
		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddOfferDialog();
			}
		};
		btAdd.addActionListener(addListener);
		return btAdd;
	}

	// returns a button to refresh the shopScreen
	public JButton getRefreshButton() {
		JButton btRefresh = Util.getCustomButton("refresh");
		ActionListener refreshListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCurrentOffers();
			}
		};
		btRefresh.addActionListener(refreshListener);
		return btRefresh;
	}

}
