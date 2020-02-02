package com.example.xml.team3.service;

import com.example.xml.team3.model.review.Review;

public interface ReviewService {

	public String createNewReview(Review review) throws Exception;

	public Review findById(String id) throws Exception;

	public String updateReview(String id, Review review) throws Exception;

	public boolean deleteReview(String id) throws Exception;
}
