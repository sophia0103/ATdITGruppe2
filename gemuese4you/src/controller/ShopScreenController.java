package controller;

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
import javax.swing.SwingUtilities;

import gemuese4you.Util;
import model.Offer;
import view.AddOfferDialogView;
import view.GetOfferDialogView;

/**
 * @author I518189
 * Represents the logic behind the shop screen UI.
 */
public class ShopScreenController {
	private JPanel panelOffer;
	private ArrayList<Offer> offerList;
	private JPanel shopScreenUI;
	private Connection connection;
	public static int lastOfferID;

	public ShopScreenController(JPanel shopScreenUI) {
		panelOffer = new JPanel();
		offerList = new ArrayList<Offer>();
		this.shopScreenUI = shopScreenUI;

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Refreshes the UI.
	 */
	public void refresh() {
		showCurrentOffers();
		SwingUtilities.updateComponentTreeUI(shopScreenUI);
	}

	
	/**
	 * Displays existing offers.
	 */
	public void showCurrentOffers() {
		// panel has to be removed and added again in order to fetch new offers which
		// might have been created in the process
		if (panelOffer != null) {
			shopScreenUI.remove(panelOffer);
			panelOffer = null;
		}
		readOffers();
		panelOffer = new JPanel(new GridLayout(offerList.size(), 1));
		panelOffer.setBackground(Util.orange);
		for (int i = 0; i < offerList.size(); i++) {
			panelOffer.add(getOfferPanel(offerList.get(i)));
		}
		shopScreenUI.add(panelOffer, BorderLayout.CENTER);
	}

	/**
	 * Reads the current offers in the database and adds them to the offer list.
	 */
	public void readOffers() {
		offerList.clear();
		try {
			String today = Util.returnDateAsString();
			Statement statement = connection.createStatement();
			String queryOffers = "SELECT * FROM offers WHERE exp_date > '" + today + "' ORDER BY distance";
			ResultSet resultOffers = statement.executeQuery(queryOffers);
			resultOffers.next();
			while (!resultOffers.isAfterLast() && Util.checkDatabaseEntries("offerID", "offers") && resultOffers != null
					&& resultOffers.getString(2) != null) {
				int offerID = resultOffers.getInt(1);
				String userID = resultOffers.getString(2);
				int distance = resultOffers.getInt(3);
				String expDate = resultOffers.getString(4);
				double price = resultOffers.getDouble(5);
				offerList.add(new Offer(offerID, userID, distance, expDate, price));
				resultOffers.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a panel for each offer in order to display them on the shopScreen.
	 * @param offer Specifies the offer for which a JPanel is created.
	 * @return Returns a JPanel with the information of the offer.
	 */
	public JPanel getOfferPanel(Offer offer) {
		JPanel panelOffer = new JPanel(new BorderLayout());
		JLabel panelFooter = new JLabel(offer.getUserID() + "�s offer is " + offer.getDistance() + " m away.");
		panelOffer.setBackground(new Color(255, 237, 203));
		JButton buttonIcon = Util.getCustomButton(offer.getProductList().get(0));
		ActionListener offerListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GetOfferDialogView(offer);
			}
		};
		buttonIcon.addActionListener(offerListener);
		panelOffer.add(panelFooter, BorderLayout.SOUTH);
		panelOffer.add(buttonIcon, BorderLayout.CENTER);
		return panelOffer;
	}

	/**
	 * Action which is performed when the add offer button is clicked.
	 * @return Returns a listener for the add offer button.
	 */
	public ActionListener getAddListener() {
		ActionListener addListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddOfferDialogView();
			}
		};
		return addListener;
	}

	/**
	 * Action which is performed when the refresh button is clicked.
	 * @return Returns a listener for the refresh button.
	 */
	public ActionListener getRefreshListener() {
		ActionListener refreshListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		};
		return refreshListener;
	}
}
