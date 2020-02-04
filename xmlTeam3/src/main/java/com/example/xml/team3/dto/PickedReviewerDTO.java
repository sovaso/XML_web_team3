package com.example.xml.team3.dto;

public class PickedReviewerDTO {
	protected String editorUsername;
	protected String reviewerUsername;
	protected String scientificWorkId;

	public String getEditorUsername() {
		return editorUsername;
	}

	public void setEditorUsername(String editorUsername) {
		this.editorUsername = editorUsername;
	}

	public String getReviewerUsername() {
		return reviewerUsername;
	}

	public void setReviewerUsername(String reviewerUsername) {
		this.reviewerUsername = reviewerUsername;
	}

	public String getScientificWorkId() {
		return scientificWorkId;
	}

	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}

	public PickedReviewerDTO(String editorUsername, String reviewerUsername, String scientificWorkId) {
		super();
		this.editorUsername = editorUsername;
		this.reviewerUsername = reviewerUsername;
		this.scientificWorkId = scientificWorkId;
	}

	public PickedReviewerDTO() {
		super();
	}

}
