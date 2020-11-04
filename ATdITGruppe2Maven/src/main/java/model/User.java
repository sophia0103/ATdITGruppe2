package model;

/**
 * @author Martin
 * Represents an object from the database entity 'users'.
 */
public class User {
	
	private String username;
	private String password;
	private int isFarmer;
	
	public User(String username, String password, int isFarmer) {
		this.username = username;
		this.password = password;
		this.isFarmer = isFarmer;
	}
	
	/**
	 * @return Returns isFarmer attribute of a user.
	 */
	public int getIsFarmer() {
		return isFarmer;
	}
	
	/**
	 * @param isFarmer changes the role of the user.
	 */
	public void setIsFarmer(int isFarmer) {
		this.isFarmer = isFarmer;
	}
	
	/**
	 * @return Returns the password of an user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password changes the password to a new password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the username of an user.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username is the username the old username is changed to.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
}
