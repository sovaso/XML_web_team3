package com.example.xml.team3.dto;

import java.util.List;

public class ReviewDTO {
	List<CommentDTO> comments;
	GradeDTO grades;
	String scientificWorkId;
	String summaryComment;
	String reviewId;

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public GradeDTO getGrades() {
		return grades;
	}

	public void setGrades(GradeDTO grades) {
		this.grades = grades;
	}

	public String getScientificWorkId() {
		return scientificWorkId;
	}

	public void setScientificWorkId(String workflowId) {
		this.scientificWorkId = workflowId;
	}

	public String getSummaryComment() {
		return summaryComment;
	}

	public void setSummaryComment(String summaryComment) {
		this.summaryComment = summaryComment;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public ReviewDTO(List<CommentDTO> comments, GradeDTO grades, String scientificWorkId, String summaryComment,
			String reviewId) {
		super();
		this.comments = comments;
		this.grades = grades;
		this.scientificWorkId = scientificWorkId;
		this.summaryComment = summaryComment;
		this.reviewId = reviewId;
	}

	public ReviewDTO() {
		super();
	}

}
