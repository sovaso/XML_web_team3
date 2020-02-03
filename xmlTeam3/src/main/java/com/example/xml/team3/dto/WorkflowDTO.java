package com.example.xml.team3.dto;

public class WorkflowDTO {
	String authorUsername;
	String editorUsername;
	String reviewerUsername;
	String scientificWorkId;
	String id;

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public String getEditorUsername() {
		return editorUsername;
	}

	public void setEditorUsername(String editorUsername) {
		this.editorUsername = editorUsername;
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

	public WorkflowDTO(String authorUsername, String reviewerUsername, String editorUsername, String scientificWorkId,
			String id) {
		super();
		this.reviewerUsername = reviewerUsername;
		this.authorUsername = authorUsername;
		this.editorUsername = editorUsername;
		this.scientificWorkId = scientificWorkId;
		this.id = id;
	}

	public WorkflowDTO() {
		super();
	}

	public String getReviewerUsername() {
		return reviewerUsername;
	}

	public void setReviewerUsername(String reviewerUsername) {
		this.reviewerUsername = reviewerUsername;
	}

}
