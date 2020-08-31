package gemuese4you;

import java.awt.Color;
import java.awt.Insets;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Util {
	private static Connection connection;
	public static Color orange = new Color(255, 229, 204);
	
	public Util() {
		
	}

	//returns a connection to our database
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		}
		return connection;
	}

	//returns the current date as a String
	public static String returnDateAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
		}

	//parses a String to Date
	public static Date returnStringAsDate(String sDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(sDate);
		return date;
		}
	
	//returns the current date as Date
	public static Date returnDate() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return date;
	}

	//returns a custom button with an image
	public static JButton getCustomButton(String iconName) {
		JButton customButton = new JButton();
		customButton = new JButton();
		customButton.setBackground(orange);
		customButton.setIcon(new ImageIcon("images/"+iconName+".png"));
		customButton.setMargin(new Insets(0, 0, 0, 0));
		customButton.setBorder(null);
		return customButton;
	}
	
	//check if there are existing entries in the database table
	//otherwise program is stuck in an endless loop
	public static boolean checkDatabaseEntries(String attributeName, String dbTableName) {
		try {
			connection = getConnection();
			Statement stmt = connection.createStatement();
			String queryEmpty = "SELECT COUNT("+attributeName+")FROM "+dbTableName;
			ResultSet rsEmpty = stmt.executeQuery(queryEmpty);
			rsEmpty.next();
			int queryLength = rsEmpty.getInt(1);
			if(queryLength>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
