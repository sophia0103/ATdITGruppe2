package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	private static Connection connection;

	public Offer(int offerID, String userID, int distance, String expDate, double price) {
		this.offerID = offerID;
		this.userID = userID;
		this.price = price;
		this.distance = distance;
		this.expDate = expDate;

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.productList = this.getProductList();
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return Returns a ArrayList with all the products in an offer as Strings.
	 */
	public ArrayList<String> getProductList() {
		try {
			ArrayList<String> productList = new ArrayList<String>();
			Statement statement = connection.createStatement();
			String queryGetProductList = "SELECT productName FROM products JOIN productsinoffer WHERE productsinoffer.productID = products.productID AND offerID ="
					+ offerID;
			ResultSet resultGetProductList = statement.executeQuery(queryGetProductList);
			while (!resultGetProductList.isAfterLast()) {
				if (resultGetProductList.next()) {
					productList.add(resultGetProductList.getString(1));
				}
			}
			return productList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @return Returns the ID/number of the last offer in the database table offers.
	 */
	public static int getLastOfferID() {
		int lastOfferID;
		try {
			if (connection == null) {
				connection = Util.getConnection();
			}
			Statement statement = connection.createStatement();
			String lastOfferIDQuery = "SELECT COUNT(offerID) FROM offers";
			ResultSet resultLastOfferID = statement.executeQuery(lastOfferIDQuery);
			resultLastOfferID.next();
			lastOfferID = resultLastOfferID.getInt(1);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
		return lastOfferID;
	}

	/**
	 * @return Returns the offerID of an offer.
	 */
	public int getOfferID() {
		return offerID;
	}

	/**
	 * Changes the offerID of an offer.
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
	 * Changes the userID of an offer.
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
	 * Changes the distance of an offer.
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
	 * Changes the price of an offer.
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return Returns the expiration date of an offer.
	 */
	public String getDate() {
		return expDate;
	}

	/**
	 * Changes the expiration date of an offer.
	 */
	public void setDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * Changes the productList of an offer.
	 */
	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}
}
