
import java.awt.Color;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import gemuese4you.LoginScreen;

public class Util {
	private static Connection connection;
	public static Color orange = new Color(255, 229, 204);

	public Util() {

	}

	// returns a connection to our database
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		}
		return connection;
	}

	// returns the current date as a String
	public static String returnDateAsString() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}

	// parses a String to Date
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

	// returns the current date as Date
	public static Date returnDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		return date;
	}

	// returns a custom button with an image
	public static JButton getCustomButton(String iconName) {
		JButton customButton = new JButton();
		customButton = new JButton();
		customButton.setBackground(orange);
		customButton.setIcon(new ImageIcon("images/" + iconName + ".png"));
		customButton.setMargin(new Insets(0, 0, 0, 0));
		customButton.setBorder(null);
		return customButton;
	}

	// check if there are existing entries in the database table
	// otherwise program is stuck in an endless loop
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

	
	//check if the user is registered as a farmer
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
	
	//get the current password of the user
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
