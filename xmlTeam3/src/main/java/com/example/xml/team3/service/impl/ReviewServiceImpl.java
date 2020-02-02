package com.example.xml.team3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.repository.ReviewRepository;
import com.example.xml.team3.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public String createNewReview(Review review) throws Exception {
		return reviewRepository.save(review);
	}

	public Review findById(String id) throws Exception {
		return reviewRepository.findById(id);
	}

	public String updateReview(String id, Review review) throws Exception {
		return reviewRepository.update(id, review);
	}

	public boolean deleteReview(String id) throws Exception {
		return reviewRepository.delete(id);
	}

}
