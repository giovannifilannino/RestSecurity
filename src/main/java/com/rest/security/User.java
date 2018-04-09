package com.rest.security;

public class User implements UserPrincipal{
	
	private String username;
	private String role;
	private String name;
	
	public String getUsername() {
		return username;
	}

	public String getRole() {
		return role;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
