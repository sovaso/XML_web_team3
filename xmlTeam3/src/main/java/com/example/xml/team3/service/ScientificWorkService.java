package com.example.xml.team3.service;

import java.util.List;

import com.example.xml.team3.dto.SearchDTO;
import com.example.xml.team3.model.scientificwork.ScientificWork;

public interface ScientificWorkService {

	public String createNewScientificWork(ScientificWork scientificWork) throws Exception;

	public ScientificWork findById(String id) throws Exception;

	public String updateScientificWork(String id, ScientificWork scientificWork) throws Exception;

	public boolean deleteScientificWork(String id) throws Exception;

	public List<ScientificWork> findAllPublished();

	public String getUsernameByNameAndSurname(String name, String surname);

	public List<ScientificWork> findAllForConcreteUser(String username);

	public List<ScientificWork> findAllForRevision(String username);

	public List<ScientificWork> findAllSubmitted();

	public List<ScientificWork> findAllForReviewing(String reviewerUsername);

	public List<ScientificWork> searchScientificWork(SearchDTO searchDTO, String username);
	
	public List<ScientificWork> getAllForEditor();
}
