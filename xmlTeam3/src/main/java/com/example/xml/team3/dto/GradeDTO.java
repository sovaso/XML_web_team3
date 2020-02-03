package com.example.xml.team3.dto;

public class GradeDTO {
	int uniqueness;
	int keywords;
	int absract;
	int conclusions;
	int experiments;
	int layout;
	int formality;
	int generalGrade;
	int references;
	int headings;
	public int getUniqueness() {
		return uniqueness;
	}
	public void setUniqueness(int uniqueness) {
		this.uniqueness = uniqueness;
	}
	public int getKeywords() {
		return keywords;
	}
	public void setKeywords(int keywords) {
		this.keywords = keywords;
	}
	public int getAbsract() {
		return absract;
	}
	public void setAbsract(int absract) {
		this.absract = absract;
	}
	public int getConclusions() {
		return conclusions;
	}
	public void setConclusions(int conclusions) {
		this.conclusions = conclusions;
	}
	public int getExperiments() {
		return experiments;
	}
	public void setExperiments(int experiments) {
		this.experiments = experiments;
	}
	public int getLayout() {
		return layout;
	}
	public void setLayout(int layout) {
		this.layout = layout;
	}
	public int getFormality() {
		return formality;
	}
	public void setFormality(int formality) {
		this.formality = formality;
	}
	public int getGeneralGrade() {
		return generalGrade;
	}
	public void setGeneralGrade(int generalGrade) {
		this.generalGrade = generalGrade;
	}
	public int getReferences() {
		return references;
	}
	public void setReferences(int references) {
		this.references = references;
	}
	public int getHeadings() {
		return headings;
	}
	public void setHeadings(int headings) {
		this.headings = headings;
	}
	public GradeDTO(int uniqueness, int keywords, int absract, int conclusions, int experiments, int layout,
			int formality, int generalGrade, int references, int headings) {
		super();
		this.uniqueness = uniqueness;
		this.keywords = keywords;
		this.absract = absract;
		this.conclusions = conclusions;
		this.experiments = experiments;
		this.layout = layout;
		this.formality = formality;
		this.generalGrade = generalGrade;
		this.references = references;
		this.headings = headings;
	}
	public GradeDTO() {
		super();
	}
	
	
}
