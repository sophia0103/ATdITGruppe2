package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gemuese4you.Util;
import model.Job;
import model.Offer;
import view.AddOfferDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author I518189
 * Represents the logic of a dialog which opens when the user wants to create an offer.
 */
public class AddOfferDialogController implements DataController {
	private Connection connection;
	private String[] productArray;
	public ArrayList<String> productList;
	private View view;
	private int offerID, distance;
	private double price;
	private String userID, expDate;

	public AddOfferDialogController(AddOfferDialogView addOfferDialogView) {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ShopScreenController.lastOfferID = Offer.getLastOfferID();

	}


	@Override
	public void setView(View view) {
		this.view = view;
	}


	@Override
	public void startProcess(View view) {
		
		Offer data = ((DataView) view).getData();
		offerID = data.getOfferID();
		userID = data.getUserID();
		price = data.getPrice();
		distance = data.getDistance();
		expDate = data.getExpDate();
		
		setView(view);
		if (checkInputValidity()) {
			try {
				addOffer();
				readProducts();
				addProductListOfOffer();
				JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
				((AddOfferDialogView) view).dispose();
			} catch (SQLException sqlException) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check for wrong data type", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	/**
	 * Adds a valid offer and its products to the database. 
	 * Gets the products from the product text field.
	 */
	public void readProducts() {
		productArray = ((AddOfferDialogView) view).getProducts().split(",");
		productList = new ArrayList<String>();
		for (int i = 0; i < productArray.length; i++) {
			productList.add(productArray[i]);
			checkIfProductExists(productArray[i]);
		}
	}

	
	/**
	 * If one of the products doesn´t yet exist in the database, add it to the products database table.
	 * @param productName Name of the product which is checked upon.
	 */
	public void checkIfProductExists(String productName) {
		try {
			Statement statementProducts = connection.createStatement();
			int numberOfExistingProducts;
			for (int i = 0; i < productList.size(); i++) {
				String checkProductExists = "SELECT COUNT(productName) FROM products WHERE productName = '"
						+ productName + "'";
				ResultSet resultProductExists = statementProducts.executeQuery(checkProductExists);
				resultProductExists.next();
				numberOfExistingProducts = resultProductExists.getInt(1);
				if (numberOfExistingProducts == 0) {
					addNonExistingProduct(productName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/** Adds a non existing product to the product database table.
	 * @param productName Name of the product which should be added in the database table.
	 */
	public void addNonExistingProduct(String productName) {
		try {
			Statement statementNonExistingProduct = connection.createStatement();
			String queryNonExistingProduct = "INSERT INTO products(productName) VALUES ('" + productName + "')";
			statementNonExistingProduct.executeQuery(queryNonExistingProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**Inserts a new offer into the offers database table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addOffer() throws SQLException {
		Statement statementAddOffer = connection.createStatement();
		// Auto increment in SQL doesn´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;

		String queryAddOffer = "INSERT INTO offers VALUES (" + ShopScreenController.lastOfferID + ",'"
				+ userID + "'," + distance + ",'" + expDate + "',"
				+ price + ")";
		statementAddOffer.execute(queryAddOffer);
	}

	/**
	 * Adds products of an offer to the database table productsInOffer.
	 */
	public void addProductListOfOffer() {
		try {
			Statement statementAddProductList = connection.createStatement();
			for (int i = 0; i < productList.size(); i++) {
				String productOffer = "INSERT INTO productsinoffer VALUES (" + ShopScreenController.lastOfferID
						+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
				statementAddProductList.execute(productOffer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	/** Checks if the input values of the input fields are valid.
	 * @return Returns true if the input is valid, otherwise false.
	 */
	@Override
	public boolean checkInputValidity() {
		if (((AddOfferDialogView) view).getProducts().equals("")
			 || expDate.equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// check if the expiration date is before the current date
		if (Util.returnStringAsDate(expDate).compareTo(Util.returnDate()) < 0) {
			JOptionPane.showMessageDialog(null, "Expiration date has to be later than current date.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			// Check if input for product is a number (invalid)
			Integer.parseInt(((AddOfferDialogView) view).getProducts());
			JOptionPane.showMessageDialog(null, "Check for wrong datatype", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

}
