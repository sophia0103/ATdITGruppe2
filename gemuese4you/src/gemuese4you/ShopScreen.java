package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShopScreen extends Screen {
	private static ArrayList<Offer> offerList;
	private static Connection connection;
	private JPanel panelOffer, panelEast, panelTitlebar;
	private JButton buttonAddOffer, buttonRefresh;
	public static int lastOfferID;

	public ShopScreen() {
		
		this.setLayout(new BorderLayout());
		panelTitlebar = getTitleBar("Shop");
		buttonAddOffer = getAddOfferButton();
		buttonRefresh = getRefreshButton();
		
		// AddOfferButton only exists for farmers (scope)
		if (Util.isUserFarmer()) {
			panelEast = new JPanel(new GridLayout(1, 2));
			panelEast.add(buttonRefresh);
			panelEast.add(buttonAddOffer);
		} else {
			panelEast = new JPanel();
			panelEast.add(buttonRefresh);
		}
		panelTitlebar.add(panelEast, BorderLayout.EAST);
		this.add(panelTitlebar, BorderLayout.NORTH);

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
		if (panelOffer != null) {
			this.remove(panelOffer);
			panelOffer = null;
		}
		readOffers();
		panelOffer = new JPanel(new GridLayout(offerList.size(), 1));
		for (int i = 0; i < offerList.size(); i++) {
			panelOffer.add(getOfferPanel(offerList.get(i)));
		}
		this.add(panelOffer, BorderLayout.CENTER);
	}

	// reads the current offers in the database and adds them to the offer list
	public void readOffers() {
		offerList.clear();
		try {
			String today = Util.returnDateAsString();
			Statement statement = connection.createStatement();
			String queryOffers = "SELECT * FROM offers WHERE exp_date > '" + today + "' ORDER BY distance";
			ResultSet resultOffers = statement.executeQuery(queryOffers);
			while (!resultOffers.isAfterLast() && Util.checkDatabaseEntries("offerID", "offers")) {
				if (resultOffers.next()) {
					int offerID = resultOffers.getInt(1);
					String userID = resultOffers.getString(2);
					int distance = resultOffers.getInt(3);
					String expDate = resultOffers.getString(4);
					double price = resultOffers.getDouble(5);
					offerList.add(new Offer(offerID, userID, distance, expDate, price));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// returns a panel for each offer in order to display them on the shopScreen
	public JPanel getOfferPanel(Offer offer) {
		JPanel panelOffer = new JPanel(new BorderLayout());
		JLabel panelFooter = new JLabel(offer.getUserID() + "´s offer is " + offer.getDistance() + " m away.");
		panelOffer.setBackground(new Color(255, 237, 203));
		JButton buttonIcon = Util.getCustomButton(offer.getProductList().get(0));
		ActionListener offerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GetOfferDialog(offer);
			}
		};
		buttonIcon.addActionListener(offerListener);
		panelOffer.add(panelFooter, BorderLayout.SOUTH);
		panelOffer.add(buttonIcon, BorderLayout.CENTER);
		return panelOffer;
	}

	// returns a button to add an offer
	public JButton getAddOfferButton() {
		JButton buttonAdd = Util.getCustomButton("add");
		// Function which is called when the add offer button is pressed
		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddOfferDialog();
			}
		};
		buttonAdd.addActionListener(addListener);
		return buttonAdd;
	}

	// returns a button to refresh the shopScreen
	public JButton getRefreshButton() {
		JButton buttonRefresh = Util.getCustomButton("refresh");
		ActionListener refreshListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCurrentOffers();
			}
		};
		buttonRefresh.addActionListener(refreshListener);
		return buttonRefresh;
	}

}
