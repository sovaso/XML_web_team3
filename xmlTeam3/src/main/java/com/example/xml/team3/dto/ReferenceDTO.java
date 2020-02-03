package com.example.xml.team3.dto;

public class ReferenceDTO {
	String scientificWorkId;

	public String getScientificWorkId() {
		return scientificWorkId;
	}

	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}

	public ReferenceDTO(String scientificWorkId) {
		super();
		this.scientificWorkId = scientificWorkId;
	}

	public ReferenceDTO() {
		super();
	}

}
