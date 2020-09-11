package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gemuese4you.Util;

//represents the database entity offers
public class Offer {
	private int offerID, distance;
	private double price;
	private String userID, date;
	private ArrayList<String> productList;
	private static Connection connection;

	public Offer(int offerID, String userID, int distance, String date, double price) {
		this.offerID = offerID;
		this.userID = userID;
		this.price = price;
		this.distance = distance;
		this.date = date;

		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.productList = this.getProductList();
	}

	// returns a list with all the products in an offer
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

	// get the ID of the last offer in the database table
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

	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}
}
