package gemuese4you;

import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;

public class Util {
	private static Connection connection;

	public Util() {
		// TODO Auto-generated constructor stub
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
		Calendar c = formatter.getCalendar();
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
		}
}
