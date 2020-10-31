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
import gemuese4you.Validator;
import model.Job;
import model.Offer;
import view.AddOfferDialogView;
import view.DataView;
import view.LoginScreenView;
import view.View;

/**
 * @author I518189 Represents the logic of a dialog which opens when the user
 *         wants to create an offer.
 */
public class AddOfferDialogController implements DataController {
	private String[] productArray;
	public ArrayList<String> productList;
	private View view;

	public AddOfferDialogController(AddOfferDialogView addOfferDialogView) {

		ShopScreenController.lastOfferID = Offer.getLastOfferID();

	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void startProcess(View view){
		String[] inputArray = ((DataView) view).getData();
		setView(view);
		if (Validator.isValidOffer(inputArray)) {
			try {
				Offer offer = createModel(inputArray);
				readProducts(inputArray[3]);
				addOffer(offer);
				addProductListOfOffer(offer);
				JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
				((AddOfferDialogView) view).dispose();
			} catch (SQLException | ClassNotFoundException exception) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check for wrong data type.", "Error", JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Unable to create offer - Check for wrong data type.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds a valid offer and its products to the database. Gets the products from
	 * the product text field.
	 * @throws ClassNotFoundException 
	 */
	public void readProducts(String products) throws ClassNotFoundException {
		productArray = products.split(",");
		productList = new ArrayList<String>();
		for (int i = 0; i < productArray.length; i++) {
			productList.add(productArray[i]);
			checkIfProductExists(productArray[i]);
		}
	}

	/**
	 * If one of the products doesn´t yet exist in the database, add it to the
	 * products database table.
	 * 
	 * @param productName Name of the product which is checked upon.
	 */
	public void checkIfProductExists(String productName) throws ClassNotFoundException {
		try {
			Statement statementProducts = Util.getConnection().createStatement();
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
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find the product you were looking for, please retry or add a new product.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Adds a non existing product to the product database table.
	 * 
	 * @param productName Name of the product which should be added in the database
	 *                    table.
	 */
	public void addNonExistingProduct(String productName)throws SQLException, ClassNotFoundException {
		try {
			Statement statementNonExistingProduct = Util.getConnection().createStatement();
			String queryNonExistingProduct = "INSERT INTO products(productName) VALUES ('" + productName + "')";
			statementNonExistingProduct.executeQuery(queryNonExistingProduct);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Product could not be added to the list.", "Error",
					JOptionPane.ERROR_MESSAGE);		
			e.printStackTrace();
			}
	}

	/**
	 * Inserts a new offer into the offers database table.
	 * 
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addOffer(Offer offer) throws SQLException {
		Statement statementAddOffer;
		try {
			statementAddOffer = Util.getConnection().createStatement();
			String queryAddOffer = "INSERT INTO offers VALUES (" + offer.getOfferID() + ",'" + offer.getUserID() + "',"
					+ offer.getDistance() + ",'" + offer.getExpDate() + "'," + offer.getPrice() + ")";
			statementAddOffer.execute(queryAddOffer);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Offer could not be added.", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	/**
	 * Adds products of an offer to the database table productsInOffer.
	 */
	public void addProductListOfOffer(Offer offer) throws SQLException, ClassNotFoundException {
		try {
			Statement statementAddProductList = Util.getConnection().createStatement();
			for (int i = 0; i < productList.size(); i++) {
				String productOffer = "INSERT INTO productsinoffer VALUES (" + offer.getOfferID()
						+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
				statementAddProductList.execute(productOffer);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "You tried to add a product which is not in the current product list.", "Error",
					JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, productList.toString(), "Error",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public <T>T createModel(String[] inputArray) {
		int offerID = ShopScreenController.lastOfferID;
		String userID = LoginScreenView.userID;
		int price = Integer.parseInt(inputArray[0]);
		int distance = Integer.parseInt(inputArray[1]);
		String exp_date = inputArray[2];
		Offer offer = new Offer(offerID, userID, distance, exp_date, price);
		// Auto increment in SQL doesn´t work properly, so we do it manually
		ShopScreenController.lastOfferID++;
		return (T) offer;
	}

}
