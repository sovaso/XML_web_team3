package com.example.xml.team3.dto;

public class HeaderDTO {
	String received;
	String revised;
	String accepted;
	
	
	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}
	public String getRevised() {
		return revised;
	}
	public void setRevised(String revised) {
		this.revised = revised;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public HeaderDTO(String received, String revised, String accepted) {
		super();
		this.received = received;
		this.revised = revised;
		this.accepted = accepted;
	}
	public HeaderDTO() {
		super();
	}
	
	
	
	
	

}
