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
	public void startProcess(View view) {
		String[] inputArray = ((DataView) view).getData();
		setView(view);
		if (Validator.isValidOffer(inputArray)) {
			try {
				Offer offer = createModel(inputArray);
				readProducts();
				addOffer(offer);
				addProductListOfOffer(offer);
				JOptionPane.showMessageDialog(null, "Offer was successfully created! :)");
				((AddOfferDialogView) view).dispose();
			} catch (SQLException sqlException) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, "Check for wrong data type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Unable to create offer - Check for wrong data type", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds a valid offer and its products to the database. Gets the products from
	 * the product text field.
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
	 * If one of the products doesn´t yet exist in the database, add it to the
	 * products database table.
	 * 
	 * @param productName Name of the product which is checked upon.
	 */
	public void checkIfProductExists(String productName) {
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
			e.printStackTrace();
		}
	}

	/**
	 * Adds a non existing product to the product database table.
	 * 
	 * @param productName Name of the product which should be added in the database
	 *                    table.
	 */
	public void addNonExistingProduct(String productName) {
		try {
			Statement statementNonExistingProduct = Util.getConnection().createStatement();
			String queryNonExistingProduct = "INSERT INTO products(productName) VALUES ('" + productName + "')";
			statementNonExistingProduct.executeQuery(queryNonExistingProduct);
		} catch (SQLException | ClassNotFoundException e) {
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Adds products of an offer to the database table productsInOffer.
	 */
	public void addProductListOfOffer(Offer offer) {
		try {
			Statement statementAddProductList = Util.getConnection().createStatement();
			for (int i = 0; i < productList.size(); i++) {
				String productOffer = "INSERT INTO productsinoffer VALUES (" + offer.getOfferID()
						+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
				statementAddProductList.execute(productOffer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
