package com.example.xml.team3.dto;

import java.util.List;

public class ScientificWorkDTO {
	HeaderDTO headerDTO;
	String title;
	List<AuthorDTO> authorsDTO;
	AbstractDTO abstractDTO;
	List<String> paragraphs;
	List<ReferenceDTO> referenceDTO;
	List<String> comments;

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

	public ScientificWorkDTO(HeaderDTO headerDTO, String title, List<AuthorDTO> authorsDTO, AbstractDTO abstractDTO,
			List<String> paragraphs, List<ReferenceDTO> referenceDTO, List<String> comments) {
		super();
		this.headerDTO = headerDTO;
		this.title = title;
		this.authorsDTO = authorsDTO;
		this.abstractDTO = abstractDTO;
		this.paragraphs = paragraphs;
		this.referenceDTO = referenceDTO;
		this.comments = comments;
	}

	public ScientificWorkDTO() {
		super();
	}

}
