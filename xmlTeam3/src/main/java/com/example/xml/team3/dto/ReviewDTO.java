package com.example.xml.team3.dto;

import java.util.List;

public class ReviewDTO {
	List<CommentDTO> comments;
	GradeDTO grades;
	String workflowId;
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
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
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
	public ReviewDTO(List<CommentDTO> comments, GradeDTO grades, String workflowId, String summaryComment,
			String reviewId) {
		super();
		this.comments = comments;
		this.grades = grades;
		this.workflowId = workflowId;
		this.summaryComment = summaryComment;
		this.reviewId = reviewId;
	}
	public ReviewDTO() {
		super();
	}
	
	
	
	
	

}
