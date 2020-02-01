package com.example.xml.team3.dto;

public class LoginRequestDTO {
	protected String username;
	protected String password;

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

	public LoginRequestDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginRequestDTO() {
		super();
	}

}
