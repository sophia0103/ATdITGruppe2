package model;

/**
 * @author Martin
 * Represents user input of the login screen.
 */
public class UserCredentials {
	private String username;
	private String password;
	
	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return Returns password attribute of a user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return Returns username attribute of a user.
	 */
	public String getUsername() {
		return username;
	}
	
}
