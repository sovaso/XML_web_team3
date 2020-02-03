package com.example.xml.team3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.repository.ReviewRepository;
import com.example.xml.team3.service.ReviewService;

@Service
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

	public String getWorkflowIdByScientificWorkId(String scientificWorkId) throws Exception {
		return reviewRepository.getWorkflowIdByScientificWorkId(scientificWorkId);
	}

	public List<Review> getAllByScientificWorkId(String scientificWorkId) throws Exception {
		return reviewRepository.getAllByScientificWorkId(scientificWorkId);
	}

}
