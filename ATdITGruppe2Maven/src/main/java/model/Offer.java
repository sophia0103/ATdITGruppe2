package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gemuese4you.Starter;
import gemuese4you.Util;

/**
 * @author I518189
 * Represents an object of the database entity 'offers'.
 */
public class Offer {
	private int offerID, distance;
	private double price;
	private String userID, expDate;
	private ArrayList<String> productList;

	public Offer(int offerID, String userID, int distance, String expDate, double price) {
		this.offerID = offerID;
		this.userID = userID;
		this.price = price;
		this.distance = distance;
		this.expDate = expDate;
	}

	/**
	 * @return Returns a ArrayList with all the products in an offer as Strings.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 * @throws Exception That is a custom exception which is thrown when there are faulty or missing database entries which prohibit
	 * the program from starting properly.
	 */
	public ArrayList<String> getProductList() throws Exception, SQLException {
		ArrayList<String> productList = new ArrayList<String>();
		Statement statement = Util.getConnection().createStatement();
		String queryGetProductList = "SELECT productName FROM products JOIN productsinoffer WHERE productsinoffer.productID = products.productID AND offerID ="
				+ offerID;
		ResultSet resultGetProductList = statement.executeQuery(queryGetProductList);
		if((Util.checkDatabaseEntries("productID", "productsinoffer") && !Util.checkDatabaseEntries("offerID", "offers"))
				|| (!Util.checkDatabaseEntries("productID", "productsinoffer") && Util.checkDatabaseEntries("offerID", "offers")) || resultGetProductList == null) {
			throw new Exception(Starter.content.getString("dataBaseMissingEntriesError"));
		}
		while (!resultGetProductList.isAfterLast()) {
			if (resultGetProductList.next()) {
				productList.add(resultGetProductList.getString(1));
			}
		}
		return productList;
	}

	/**
	 * @return Returns the ID/number of the last offer in the database table offers.
	 * @throws SQLException Throws Exception if the SQL statement is incorrect.
	 */
	public static int getLastOfferID() throws SQLException{
		int lastOfferID;
		Statement statement = Util.getConnection().createStatement();
		String lastOfferIDQuery = "SELECT COUNT(offerID) FROM offers";
		ResultSet resultLastOfferID = statement.executeQuery(lastOfferIDQuery);
		resultLastOfferID.next();
		lastOfferID = resultLastOfferID.getInt(1);
		return lastOfferID;
	}

	/**
	 * @param productList Changes the product List of the offer to the value of this parameter.
	 */
	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}

	/**
	 * @return Returns the offerID of an offer.
	 */
	public int getOfferID() {
		return offerID;
	}


	/**
	 * @param offerID Changes the offer ID of the offer to the value of this parameter.
	 */
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	/**
	 * @return Returns the userID of an offer.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID Changes the user ID of the offer to the value of this parameter.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return Returns the distance of an offer.
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance Changes the distance of the offer to the value of this parameter.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return Returns the price of an offer.
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price Changes the price of an offer to the value of this parameter.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return Returns the expiration date of the offer.
	 */
	public String getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate Changes the expiration date of the offer to the value of this parameter.
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

}
