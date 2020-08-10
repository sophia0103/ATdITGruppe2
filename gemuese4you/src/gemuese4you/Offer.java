package gemuese4you;

import java.sql.*;
import java.util.ArrayList;

public class Offer {
	int offerID, farmerID, distance;
	ArrayList<String> productList;
	private static Connection connection;

	public Offer(int offerID, int farmerID,int distance, ArrayList<String> productList) {
		this.offerID = offerID;
		this.farmerID = farmerID;
		this.distance = distance;
		this.productList = productList;
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		}
		return connection;
	}
	
	public String getFarmerFirstName() {
		try {
			Statement stmt = connection.createStatement();
			String queryGFFN = "SELECT firstName FROM farmer WHERE farmerID = "+farmerID;
			ResultSet resGFFN = stmt.executeQuery(queryGFFN);
			return resGFFN.getString(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
