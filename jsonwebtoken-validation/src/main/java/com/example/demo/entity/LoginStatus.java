package com.example.demo.entity;

public class LoginStatus {
	
	private String message;
	
	private String token;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginStatus(String message, String token) {
		this.message = message;
		this.token = token;
	}
	
	public LoginStatus() {
		
	}

}
