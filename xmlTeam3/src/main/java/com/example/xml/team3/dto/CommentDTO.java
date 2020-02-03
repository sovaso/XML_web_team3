package com.example.xml.team3.dto;

public class CommentDTO {
	String value;
	String refId;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public CommentDTO(String value, String refId) {
		super();
		this.value = value;
		this.refId = refId;
	}
	public CommentDTO() {
		super();
	}
	
	

}
