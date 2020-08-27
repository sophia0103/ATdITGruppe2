package gemuese4you;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddOfferDialog extends JFrame implements ActionListener {
	private JTextField tName, tDist, tProducts;
	private JLabel lName, lDist, lProducts;
	private JPanel pInput;
	private JButton btSave;
	static Connection connection;

	public AddOfferDialog() {
		Container c = getContentPane();
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		c.setBackground(Util.orange);
		c.setLayout(new BorderLayout());
		pInput = new JPanel(new GridLayout(6, 1));
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
		pInput.setBackground(Util.orange);
		btSave = Util.getCustomButton("save");
		c.add(pInput, BorderLayout.CENTER);
		c.add(btSave, BorderLayout.SOUTH);
		this.setVisible(true);
		this.setSize(500, 500);

		shopScreen.lastOfferID = getLastOfferID();

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
				String checkQuery = "SELECT COUNT(productName) FROM products WHERE productName = '" + productList.get(i)
						+ "'";
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
			String saveOffer = "INSERT INTO offers VALUES (" + shopScreen.lastOfferID + ",'" + tName.getText() + "',"
					+ tDist.getText() + ",'" + date + "' )";
			stmt.execute(saveOffer);
			for (int i = 0; i < productList.size(); i++) {
				String productOffer = "INSERT INTO productsinoffer VALUES (" + shopScreen.lastOfferID
						+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
				stmt.execute(productOffer);
			}
			JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
			this.dispose();
		}
		catch(SQLIntegrityConstraintViolationException e1) {
			JOptionPane.showMessageDialog(null, "Check for wrong data type or empty field", "Error", JOptionPane.ERROR_MESSAGE);
		}catch(SQLSyntaxErrorException e2) {
			JOptionPane.showMessageDialog(null, "Check for wrong data type or empty field", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e3) {
			e3.printStackTrace();
		}
	}

	
	//get the ID of the last offer in the database table
	public static int getLastOfferID() {
		int lastOfferID;
		try {
			Statement stmt = connection.createStatement();
			String lastOfferIDQuery = "SELECT COUNT(offerID) FROM offers";
			ResultSet rsLast = stmt.executeQuery(lastOfferIDQuery);
			rsLast.next();
			lastOfferID = rsLast.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return lastOfferID;
	}
	
	public boolean isUserAuthorized(String userID) {
		return true;
	}
}
