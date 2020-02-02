package com.example.xml.team3.dto;

import java.util.List;

public class AbstractDTO {
	String purpose;
	String design;
	String findings;
	String limitations;
	String originality;
	String type;
	List<String> keywords;
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getFindings() {
		return findings;
	}
	public void setFindings(String findings) {
		this.findings = findings;
	}
	public String getLimitations() {
		return limitations;
	}
	public void setLimitations(String limitations) {
		this.limitations = limitations;
	}
	public String getOriginality() {
		return originality;
	}
	public void setOriginality(String originality) {
		this.originality = originality;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public AbstractDTO(String purpose, String design, String findings, String limitations, String originality,
			String type, List<String> keywords) {
		super();
		this.purpose = purpose;
		this.design = design;
		this.findings = findings;
		this.limitations = limitations;
		this.originality = originality;
		this.type = type;
		this.keywords = keywords;
	}
	public AbstractDTO() {
		super();
	}
	
	

}
