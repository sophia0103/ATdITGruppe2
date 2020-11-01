package model;

public class User {
	
	private String username;
	private String password;
	private int isFarmer;
	
	public User(String username, String password, int isFarmer) {
		this.username = username;
		this.password = password;
		this.isFarmer = isFarmer;
	}
	
	public int getIsFarmer() {
		return isFarmer;
	}
	
	public void setIsFarmer(int isFarmer) {
		this.isFarmer = isFarmer;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
