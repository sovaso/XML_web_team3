package com.example.xml.team3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.dto.SearchDTO;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.repository.ScientificWorkRepository;
import com.example.xml.team3.repository.UserRepository;
import com.example.xml.team3.service.ScientificWorkService;

@Service
public class ScientificWorkServiceImpl implements ScientificWorkService {

	@Autowired
	private ScientificWorkRepository scientificWorkRepository;

	@Autowired
	private UserRepository userRepository;

	public String createNewScientificWork(ScientificWork scientificWork) throws Exception {
		return scientificWorkRepository.save(scientificWork);
	}

	public ScientificWork findById(String id) throws Exception {
		return scientificWorkRepository.findById(id);
	}

	public String updateScientificWork(String id, ScientificWork scientificWork) throws Exception{
		return scientificWorkRepository.update(id, scientificWork);
	}

	public boolean deleteScientificWork(String id) throws Exception {
		return scientificWorkRepository.delete(id);
	}

	public List<ScientificWork> findAllPublished() {
		return scientificWorkRepository.findAllPublished();
	}

	public String getUsernameByNameAndSurname(String name, String surname) {
		return userRepository.getUsernameByNameAndSurname(name, surname);
	}

	public List<ScientificWork> findAllForConcreteUser(String username) {
		return scientificWorkRepository.findAllForConcreteUser(username);
	}

	public List<ScientificWork> findAllForRevision(String username) {
		return scientificWorkRepository.findAllForRevision(username);
	}

	public List<ScientificWork> findAllSubmitted() {
		return scientificWorkRepository.findAllSubmitted();
	}

	public List<ScientificWork> findAllForReviewing(String reviewerUsername) {
		return scientificWorkRepository.findAllForReviewing(reviewerUsername);
	}

	public List<ScientificWork> searchScientificWork(SearchDTO searchDTO, String username) {
		return scientificWorkRepository.searchScientificWork(searchDTO, username);
	}

	public List<ScientificWork> getAllForEditor() {
		return scientificWorkRepository.getAllForEditor();
	}

}
