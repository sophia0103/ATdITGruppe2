package gemuese4you;

import java.sql.*;
import java.util.ArrayList;

//represents the database entity offers
public class Offer {
	private int offerID, farmerID, distance;
	private Date date;
	private ArrayList<String> productList;
	private static Connection connection;

	public Offer(int offerID, int farmerID, int distance, Date date) {
		this.offerID = offerID;
		this.farmerID = farmerID;
		this.distance = distance;
		this.date = date;
		this.productList = this.getProductList();
		try {
			connection = Util.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//returns the first name of the farmer who created the offer
	public String getFarmerFirstName() {
		try {
			Statement stmt = connection.createStatement();
			String queryGFFN = "SELECT firstName FROM farmer WHERE farmerID = " + farmerID;
			ResultSet resGFFN = stmt.executeQuery(queryGFFN);
			return resGFFN.getString(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	//returns the Product List which belongs to the offer
	public ArrayList<String> getProductList() {
		try {
			ArrayList<String> productList = new ArrayList<String>();
			Statement stmt = connection.createStatement();
			String queryGPL = "SELECT productName FROM products JOIN productsinoffer WHERE productsinoffer.productID = products.productID AND offerID ="
					+ this.offerID;
			ResultSet rsGPL = stmt.executeQuery(queryGPL);
			while (!rsGPL.isAfterLast()) {
				productList.add(rsGPL.getString(0));
				rsGPL.next();
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

	public int getFarmerID() {
		return farmerID;
	}

	public void setFarmerID(int farmerID) {
		this.farmerID = farmerID;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setProductList(ArrayList<String> productList) {
		this.productList = productList;
	}
}
