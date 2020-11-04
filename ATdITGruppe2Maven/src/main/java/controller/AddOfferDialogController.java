package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import gemuese4you.Util;
import gemuese4you.Validator;
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

	@Override
	public void startProcess(View view) {
		String[] inputArray = ((DataView) view).getData();
		this.view = view;
		if (Validator.isValidOffer(inputArray)) {
			try {
				ShopScreenController.lastOfferID = Offer.getLastOfferID();
				Offer offer = createModel(inputArray);
				readProducts(inputArray[3]);
				addOffer(offer);
				addProductListOfOffer(offer);
				JOptionPane.showMessageDialog(null, Starter.content.getString("offerCreated"));
				((AddOfferDialogView) view).dispose();
			} catch (SQLException exception) {
				// Can´t check for wrong data type in inputIsValid method
				JOptionPane.showMessageDialog(null, Starter.content.getString("sqlStatementError"), "Error", JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, Starter.content.getString("unableToCreateOffer"), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds a valid offer and its products to the database. Gets the products from
	 * the product text field.
	 * @param products The products that are added to the ArrayList of products.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void readProducts(String products) throws SQLException{
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
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void checkIfProductExists(String productName) throws SQLException{
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
	}

	/**
	 * Adds a non existing product to the product database table.
	 * 
	 * @param productName Name of the product which should be added in the database
	 *                    table.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addNonExistingProduct(String productName) throws SQLException{
		Statement statementNonExistingProduct = Util.getConnection().createStatement();
		String queryNonExistingProduct = "INSERT INTO products(productName) VALUES ('" + productName + "')";
		statementNonExistingProduct.executeQuery(queryNonExistingProduct);
	}

	/**
	 * Inserts a new offer into the offers database table.
	 * @param offer The offer that is added to the database.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addOffer(Offer offer) throws SQLException{
		Statement statementAddOffer;

		statementAddOffer = Util.getConnection().createStatement();
		String queryAddOffer = "INSERT INTO offers VALUES (" + offer.getOfferID() + ",'" + offer.getUserID() + "',"
				+ offer.getDistance() + ",'" + offer.getExpDate() + "'," + offer.getPrice() + ")";
		statementAddOffer.execute(queryAddOffer);
	}

	/**
	 * Adds products of an offer to the database table productsInOffer.
	 * @param offer The offer of which the products are added to the database.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public void addProductListOfOffer(Offer offer) throws SQLException{
		Statement statementAddProductList = Util.getConnection().createStatement();
		for (int i = 0; i < productList.size(); i++) {
			String productOffer = "INSERT INTO productsinoffer VALUES (" + offer.getOfferID()
					+ ",(SELECT productID FROM products WHERE products.productName ='" + productList.get(i) + "'))";
			statementAddProductList.execute(productOffer);
		}
	}

	@Override
	public <T> T createModel(String[] inputArray) {
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
