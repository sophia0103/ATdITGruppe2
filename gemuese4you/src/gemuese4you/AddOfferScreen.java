package gemuese4you;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

public class AddOfferScreen extends JFrame implements ActionListener{
	JTextField tName, tDist, tProducts;
	JLabel lName, lDist, lProducts;
	Connection connection;

	public AddOfferScreen() {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		JPanel pInput = new JPanel(new GridLayout(6, 1));
		tName = new JTextField();
		tDist = new JTextField();
		tProducts = new JTextField();
		lName = new JLabel("Name: ");
		lDist = new JLabel("Distance: ");
		lProducts = new JLabel("Products: ");
		pInput.add(lName);
		pInput.add(tName);
		pInput.add(lDist);
		pInput.add(tDist);
		pInput.add(lProducts);
		pInput.add(tProducts);
		JButton btSave = new JButton();
		btSave.setIcon(new ImageIcon("images/save.png"));
		btSave.setMargin(new Insets(0, 0, 0, 0));
		btSave.setBorder(null);
		c.add(pInput, BorderLayout.CENTER);
		c.add(btSave, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500, 500);

		btSave.addActionListener(this);
	}

	// Reads the values of the Add Offer Dialog and saves the new offer in the
	// database
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Statement stmt = connection.createStatement();
				// create productList

				String[] productArray = tProducts.getText().split(",");
				ArrayList<String> productList = new ArrayList<String>();
				for (int i = 0; i < productArray.length; i++) {
					productList.add(productArray[i]);
				}

				// if one of the products doesn´t yet exist in the database, add it
				for (int i = 0; i < productList.size(); i++) {
					String checkQuery = "SELECT COUNT(productName) FROM products WHERE productName = '" + productList.get(i) + "'";
					ResultSet rsCheck = stmt.executeQuery(checkQuery);
					rsCheck.next();
					int queryLength = rsCheck.getInt(1);
					if (queryLength == 0) {
						String prodQuery = "INSERT INTO products(productName) VALUES ('" + productList.get(i) + "')";
						stmt.executeQuery(prodQuery);
					}
				}
				// Insert new offer into the offers database table
				String date = Util.returnDateAsString();
				shopScreen.lastOfferID++;
				String saveOffer = "INSERT INTO offers VALUES ("+ shopScreen.lastOfferID+ ",'" + tName.getText() + "',"
						+ tDist.getText() + ",'" + date + "' )";
				stmt.execute(saveOffer);
				for (int i = 0; i < productList.size(); i++) {
					String productOffer = "INSERT INTO productsinoffer VALUES ("+shopScreen.lastOfferID+",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i)
							+ "'))";
					stmt.execute(productOffer);
				}
				JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
				this.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
