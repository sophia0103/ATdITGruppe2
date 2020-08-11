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

	public JPanel readOffers(ArrayList<Offer> offerList) {
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
		return null;
	}

	public JPanel getOfferPanel(Offer offer) {

		JPanel pOffer = new JPanel(new BorderLayout());
		JPanel pFooter = new JPanel();
		JLabel lDes = new JLabel(offer.getDate() + " :" + offer.getFarmerFirstName() + "´s offer is "
				+ offer.getDistance() + " m away.");
		pFooter.add(lDes);
		pOffer.add(pFooter, BorderLayout.SOUTH);
		JButton btImage = new JButton();
		btImage.setIcon(new ImageIcon("images/" + offer.getProductList().get(0)));
		btImage.setMargin(new Insets(0, 0, 0, 0));
		btImage.setBorder(null);
		pOffer.add(btImage, BorderLayout.CENTER);
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getOfferDialog(offer);
			}
		};
		btImage.addActionListener(al);
		return pOffer;
	}

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

	class AddOfferListener implements ActionListener {
		JTextField tName, tDist, tProducts;

		@Override
		public void actionPerformed(ActionEvent e) {
			offerID++;
		}

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
//			ActionListener saveListener = new ActionListener() {
//				Statement stmt = connection.createStatement();
//				String saveQuery = "INSERT INTO offers () VALUES ()";
//			}
			return null;

		}
	}
}
