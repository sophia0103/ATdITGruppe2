

import java.awt.Color;
import java.awt.Insets;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gemuese4you.LoginScreen;

/**
 * @author I518189
 * Helper class with methods which don´t belong to a specific class.
 */
public class Util {
	private static Connection connection;
	public static Color orange = new Color(255, 229, 204);

	public Util() {

	}

	/**
	 * @return Returns a connection to the Heidi SQL database.
	 * @throws ClassNotFoundException Throws an exception if the class is not found.
	 * @throws SQLException Throws an exception if the SQL syntax is incorrect.
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		}
		return connection;
	}

	/**
	 * @return Returns the current date as a String.
	 */
	public static String returnDateAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	/**
	 * @param sDate Date as a string which should be converted into the datatype Date.
	 * @return Returns a date with the datatype Date.
	 */
	public static Date returnStringAsDate(String sDate) {
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		date = formatter.parse(sDate);
		return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return Returns the current date as a Date.
	 */
	public static Date returnDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return date;
	}

	/**
	 * @param iconName Image which should be displayed on the JButton.
	 * @return Returns a custom JButton with an image.
	 */
	public static JButton getCustomButton(String iconName) {
		JButton customButton = new JButton();
		customButton = new JButton();
		customButton.setBackground(orange);
		customButton.setIcon(new ImageIcon("images/" + iconName + ".png"));
		customButton.setMargin(new Insets(0, 0, 0, 0));
		customButton.setBorder(null);
		return customButton;
	}

	
	/**
	 * Check if there are existing entries in the database table.
	 * Otherwise program is stuck in an endless loop.
	 * @param attributeName Name of the attribute which is counted.
	 * @param dbTableName Name of the database table in which the attribute exists.
	 * @return Returns true if there are more than zero entries, otherwise false.
	 */
	public static boolean checkDatabaseEntries(String attributeName, String dbTableName) {
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			String queryEmpty = "SELECT COUNT(" + attributeName + ")FROM " + dbTableName;
			ResultSet resultEmpty = statement.executeQuery(queryEmpty);
			resultEmpty.next();
			int queryLength = resultEmpty.getInt(1);
			if (queryLength > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * @return Returns true if the logged in user is a farmer, otherwise false.
	 */
	public static boolean isUserFarmer() {
		try {
			connection = getConnection();
			Statement statement = connection.createStatement();
			String queryIsUserFarmer = "SELECT isFarmer FROM user WHERE userID = '" + LoginScreen.userID + "'";
			ResultSet resultIsUserFarmer = statement.executeQuery(queryIsUserFarmer);
			resultIsUserFarmer.next();
			if (resultIsUserFarmer.getInt(1) == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @return Returns the password of the logged in user.
	 */
	public static String getPassword() {
		Statement statementOldPassword;
		try {
			statementOldPassword = connection.createStatement();
			String queryOldPassword = "SELECT password FROM user WHERE userID ='"+LoginScreen.userID+"'";
			ResultSet resultOldPassword = statementOldPassword.executeQuery(queryOldPassword);
			resultOldPassword.next();
			String oldPassword = resultOldPassword.getString(1);
			return oldPassword;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
