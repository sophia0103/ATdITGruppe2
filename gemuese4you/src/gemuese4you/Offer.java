package gemuese4you;

import java.sql.*;
import java.util.ArrayList;

//represents the database entity offers
public class Offer {
	private int offerID, distance;
	private String userID, date;
	private ArrayList<String> productList;
	private static Connection connection;

	public Offer(int offerID, String userID, int distance, String date) {
		this.offerID = offerID;
		this.userID = userID;
		this.distance = distance;
		this.date = date;
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.productList = this.getProductList();
	}

	// returns the first name of the farmer who created the offer
//	public String getFarmerUserID() {
//		try {
//			Statement stmt = connection.createStatement();
//			String queryGFFN = "SELECT firstName FROM farmer WHERE farmerID = " + userID;
//			ResultSet resGFFN = stmt.executeQuery(queryGFFN);
//			return resGFFN.getString(0);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}

	// returns the Product List which belongs to the offer
	public ArrayList<String> getProductList() {
		try {
			ArrayList<String> productList = new ArrayList<String>();
			Statement stmt = connection.createStatement();
			String queryGPL = "SELECT productName FROM products JOIN productsinoffer WHERE productsinoffer.productID = products.productID AND offerID ="
					+ offerID;
			ResultSet rsGPL = stmt.executeQuery(queryGPL);
			String queryEmpty = "SELECT COUNT(productName)FROM products JOIN productsinoffer WHERE productsinoffer.productID = products.productID AND offerID ="
					+ +offerID;
			ResultSet rsEmpty = stmt.executeQuery(queryEmpty);
			rsEmpty.next();
			int queryLength = rsEmpty.getInt(1);
			while (!rsGPL.isAfterLast() && queryLength>0) {
				if (rsGPL.next()) {
					productList.add(rsGPL.getString(1));
				}
			}
			return productList;
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

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
