package com.mobidev;

public class User {
	int userID;
	String email;
	
	public User(int userID, String email) {
		super();
		this.userID = userID;
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}

