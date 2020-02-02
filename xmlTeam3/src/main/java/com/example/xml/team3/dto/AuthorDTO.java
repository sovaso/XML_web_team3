package com.example.xml.team3.dto;

public class AuthorDTO {
	String name;
	String universityName;
	String universityAddress;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUniversityName() {
		return universityName;
	}
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	public String getUniversityAddress() {
		return universityAddress;
	}
	public void setUniversityAddress(String universityAddress) {
		this.universityAddress = universityAddress;
	}
	public AuthorDTO(String name, String universityName, String universityAddress) {
		super();
		this.name = name;
		this.universityName = universityName;
		this.universityAddress = universityAddress;
	}
	public AuthorDTO() {
		super();
	}
	
	

}
