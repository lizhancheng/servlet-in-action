package com.servlet.model;

public class User {
	private String userId;
	private String userName;
	private int userAge;
	
	public User(String userId, String userName, int userAge) {
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUserAge() {
		return userAge;
	}
	
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	public boolean isAgeValid() {
		return userAge >= 0 && userAge <= 150;
	}
}
