package com.example.xml.team3.dto;

public class ReferenceDTO {
	String value;
	String scientificWorkId;
	String id;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getScientificWorkId() {
		return scientificWorkId;
	}
	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ReferenceDTO() {
		super();
	}
	public ReferenceDTO(String value, String scientificWorkId, String id) {
		super();
		this.value = value;
		this.scientificWorkId = scientificWorkId;
		this.id = id;
	}
	
	

}
