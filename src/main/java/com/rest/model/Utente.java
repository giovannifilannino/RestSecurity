package com.rest.model;

import java.io.Serializable;

public class Utente implements Serializable{

	private static final long serialVersionUID = 2530361545280806310L;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
