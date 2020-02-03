package com.example.xml.team3.dto;

import java.util.List;

public class ScientificWorkDTO {
	String scientificWorkId;
	HeaderDTO headerDTO;
	String title;
	List<AuthorDTO> authorsDTO;
	AbstractDTO abstractDTO;
	List<String> paragraphs;
	List<ReferenceDTO> referenceDTO;
	List<String> comments;
	String status;

	public String getScientificWorkId() {
		return scientificWorkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setScientificWorkId(String scientificWorkId) {
		this.scientificWorkId = scientificWorkId;
	}

	public HeaderDTO getHeaderDTO() {
		return headerDTO;
	}

	public void setHeaderDTO(HeaderDTO headerDTO) {
		this.headerDTO = headerDTO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorDTO> getAuthorsDTO() {
		return authorsDTO;
	}

	public void setAuthorsDTO(List<AuthorDTO> authorsDTO) {
		this.authorsDTO = authorsDTO;
	}

	public AbstractDTO getAbstractDTO() {
		return abstractDTO;
	}

	public void setAbstractDTO(AbstractDTO abstractDTO) {
		this.abstractDTO = abstractDTO;
	}

	public List<String> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<String> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public List<ReferenceDTO> getReferenceDTO() {
		return referenceDTO;
	}

	public void setReferenceDTO(List<ReferenceDTO> referenceDTO) {
		this.referenceDTO = referenceDTO;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}


	public ScientificWorkDTO(String scientificWorkId, HeaderDTO headerDTO, String title, List<AuthorDTO> authorsDTO,
			AbstractDTO abstractDTO, List<String> paragraphs, List<ReferenceDTO> referenceDTO, List<String> comments, String status) {
		super();
		this.scientificWorkId = scientificWorkId;
		this.headerDTO = headerDTO;
		this.title = title;
		this.authorsDTO = authorsDTO;
		this.abstractDTO = abstractDTO;
		this.paragraphs = paragraphs;
		this.referenceDTO = referenceDTO;
		this.comments = comments;
		this.status = status;
	}

	public ScientificWorkDTO() {
		super();
	}

}
