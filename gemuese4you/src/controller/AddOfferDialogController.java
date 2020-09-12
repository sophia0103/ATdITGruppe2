package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gemuese4you.LoginScreen;
import gemuese4you.Util;
import model.Offer;
import view.AddOfferDialogView;

/**
 * @author I518189
 * Represents the logic of a dialog which opens when the user wants to create an offer.
 */
public class AddOfferDialogController {
	private AddOfferDialogView addOfferDialogView;
	private Connection connection;
	private JTextField textFieldDistance, textFieldProducts, textFieldPrice, textFieldDate;
	private String[] productArray;
	public ArrayList<String> productList;

	public AddOfferDialogController(AddOfferDialogView addOfferDialogView) {
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ShopScreenController.lastOfferID = Offer.getLastOfferID();
		this.addOfferDialogView = addOfferDialogView;
		textFieldProducts = addOfferDialogView.getTextFieldProducts();
		textFieldDistance = addOfferDialogView.getTextFieldDistance();
		textFieldPrice = addOfferDialogView.getTextFieldPrice();
		textFieldProducts = addOfferDialogView.getTextFieldProducts();
		textFieldDate = addOfferDialogView.getTextFieldDate();
		productArray = addOfferDialogView.getProductArray();
		productList = addOfferDialogView.getProductList();

	}

	
	/**
	 * Adds a valid offer and its products to the database. 
	 * Gets the products from the product text field.
	 */
	public void readProducts() {
		productArray = textFieldProducts.getText().split(",");
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

	/** Checks if the input values of the input fields are valid.
	 * @return Returns true if the input is valid, otherwise false.
	 */
	public boolean inputIsValid() {
		if (textFieldDistance.getText().equals("") || textFieldProducts.getText().equals("")
				|| textFieldPrice.getText().equals("") || textFieldDate.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Input mustn´t be empty", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// check if the expiration date is before the current date
		if (Util.returnStringAsDate(textFieldDate.getText()).compareTo(Util.returnDate()) < 0) {
			JOptionPane.showMessageDialog(null, "Expiration date has to be later than current date.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			// Check if input for product is a number (invalid)
			Integer.parseInt(textFieldProducts.getText());
			JOptionPane.showMessageDialog(null, "Check for wrong datatype", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception e) {
			return true;
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
				+ LoginScreen.userID + "'," + textFieldDistance.getText() + ",'" + textFieldDate.getText() + "',"
				+ textFieldPrice.getText() + ")";
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

	
	/** Action which is performed if the cancel button is clicked.
	 * @return Returns a listener for the cancel button.
	 */
	public ActionListener getCancelListener() {
		ActionListener cancelListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addOfferDialogView.dispose();
			}

		};
		return cancelListener;
	}

	/**Action which is performed if the save button is clicked.
	 * @return Returns a listener for the safe button.
	 */
	public ActionListener getSaveListener() {
		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (inputIsValid()) {
					try {
						addOffer();
						readProducts();
						addProductListOfOffer();
						JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
						addOfferDialogView.dispose();
					} catch (SQLException sqlException) {
						// Can´t check for wrong data type in inputIsValid method
						JOptionPane.showMessageDialog(null, "Check for wrong data type", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		};
		return saveListener;
	}

}
