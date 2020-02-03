package com.example.xml.team3.dto;

public class WorkflowDTO {
	String authorUsername;
	String editorUsername;
	String scientiificWorkId;
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

	public String getScientiificWorkId() {
		return scientiificWorkId;
	}

	public void setScientiificWorkId(String scientiificWorkId) {
		this.scientiificWorkId = scientiificWorkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WorkflowDTO(String authorUsername, String editorUsername, String scientiificWorkId, String id) {
		super();
		this.authorUsername = authorUsername;
		this.editorUsername = editorUsername;
		this.scientiificWorkId = scientiificWorkId;
		this.id = id;
	}

	public WorkflowDTO() {
		super();
	}

}
