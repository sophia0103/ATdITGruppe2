package gemuese4you;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class shopScreen extends Screen {
	public static int offerID;
	public static ArrayList<Offer> offerList;
	Connection connection;

	public shopScreen() {
		this.setLayout(new BorderLayout());
		JPanel titlebar = getTitleBar("Shop");
		JButton btAddOffer = getAddOfferButton();
		btAddOffer.addActionListener(new AddOfferListener());
		titlebar.add(btAddOffer, BorderLayout.EAST);
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

	}

	
	//reads the current offers in the database and adds them to the offer list
	public void readOffers() {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String queryOffers = "SELECT * FROM offers";
			ResultSet resOffers = stmt.executeQuery(queryOffers);
			while (!resOffers.isAfterLast()) {
				offerList.add(
						new Offer(resOffers.getInt(1), resOffers.getInt(2), resOffers.getInt(3), resOffers.getDate(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//opens a dialog in which the attributes of the specified offer are listed
	public void getOfferDialog(Offer offer) {
		JFrame openAddOfferDialog = new JFrame();
		openAddOfferDialog.setBackground(orange);
		Container c = openAddOfferDialog.getContentPane();
		c.setBackground(orange);
		c.setLayout(new GridLayout(3, 1));
		JLabel lOfferTitle = new JLabel(offer.getFarmerFirstName() + "´s offer: ");
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

	//returns a button to add an offer
	public JButton getAddOfferButton() {
		JButton btAdd = new JButton();
		btAdd = new JButton();
		btAdd.setBackground(orange);
		btAdd.setIcon(new ImageIcon("images/add.png"));
		btAdd.setMargin(new Insets(0, 0, 0, 0));
		btAdd.setBorder(null);
		btAdd.addActionListener(new AddOfferListener());
		return btAdd;
	}

	//Function which is called when the add offer button is pressed
	class AddOfferListener implements ActionListener {
		JTextField tName, tDist, tProducts;

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame AddOfferDialog = openAddOfferDialog();
			AddOfferDialog.setVisible(true);
			AddOfferDialog.setSize(500, 500);
		}

		
		//opens a dialog to create a new offer
		public JFrame openAddOfferDialog() {
			JFrame addOfferDialog = new JFrame();
			Container c = addOfferDialog.getContentPane();
			c.setLayout(new BorderLayout());
			JPanel pInput = new JPanel(new GridLayout(3,1));
			tName = new JTextField();
			tDist = new JTextField();
			tProducts = new JTextField();
			pInput.add(tName);
			pInput.add(tDist);
			pInput.add(tProducts);
			JButton btSave = new JButton();
			btSave.setIcon(new ImageIcon("images/save.png"));
			btSave.setMargin(new Insets(0, 0, 0, 0));
			btSave.setBorder(null);
			
			// Reads the values of the Add Offer Dialog and saves the new offer in the database
			ActionListener saveListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Statement stmt = connection.createStatement();
						String saveQuery = "INSERT INTO offers (offerID, distance, date) VALUES ("+offerID+","+tDist.getText()+", "+Util.returnDateAsString()+")";
						stmt.execute(saveQuery);
						offerID++;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			};
			return addOfferDialog;

		}
	}
}
