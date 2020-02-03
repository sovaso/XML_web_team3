package com.example.xml.team3.dto;

public class CoverLetterDTO {
	String coverLetterId;
	String scientificWorkId;
	String text;
	public String getCoverLetterId() {
		return coverLetterId;
	}
	public void setCoverLetterId(String coverLetterId) {
		this.coverLetterId = coverLetterId;
	}
	public String getScientificWorkId() {
		return scientificWorkId;
	}
	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public CoverLetterDTO(String coverLetterId, String scientificWorkId, String text) {
		super();
		this.coverLetterId = coverLetterId;
		this.scientificWorkId = scientificWorkId;
		this.text = text;
	}
	public CoverLetterDTO() {
		super();
	}
	
	
	
	

}
