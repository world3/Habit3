package org.world3.habit3.entity;

/**
 * User sign on information.
 */
public class UserCredential {
	
	private String userName;
	
	private String password;
	
	public UserCredential(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
