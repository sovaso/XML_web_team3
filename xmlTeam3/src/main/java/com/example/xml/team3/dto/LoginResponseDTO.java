package com.example.xml.team3.dto;

public class LoginResponseDTO {

	private String jsonWebToken;
	private String userRoleName;

	public LoginResponseDTO() {
		super();
	}

	public LoginResponseDTO(String jsonWebToken, String userRoleName) {
		super();
		this.jsonWebToken = jsonWebToken;
		this.userRoleName = userRoleName;
	}

	public String getJsonWebToken() {
		return jsonWebToken;
	}

	public void setJsonWebToken(String jsonWebToken) {
		this.jsonWebToken = jsonWebToken;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

}
