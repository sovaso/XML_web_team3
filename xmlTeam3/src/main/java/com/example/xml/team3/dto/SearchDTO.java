package com.example.xml.team3.dto;

public class SearchDTO {
	String author; // by name or surname or name and surname
	String title;
	String text; // random text from scientific work

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SearchDTO(String author, String title, String text) {
		super();
		this.author = author;
		this.title = title;
		this.text = text;
	}

	public SearchDTO() {
		super();
	}

}
