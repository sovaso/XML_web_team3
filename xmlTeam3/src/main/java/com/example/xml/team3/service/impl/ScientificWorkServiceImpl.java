package com.example.xml.team3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.repository.ScientificWorkRepository;
import com.example.xml.team3.service.ScientificWorkService;

public class ScientificWorkServiceImpl implements ScientificWorkService {

	@Autowired
	private ScientificWorkRepository scientificWorkRepository;

	public String createNewScientificWork(ScientificWork scientificWork) throws Exception {
		return scientificWorkRepository.save(scientificWork);
	}

	public ScientificWork findById(String id) throws Exception {
		return scientificWorkRepository.findById(id);
	}

	public String updateScientificWork(String id, ScientificWork scientificWork) throws Exception {
		return scientificWorkRepository.update(id, scientificWork);
	}

	public boolean deleteScientificWork(String id) throws Exception {
		return scientificWorkRepository.delete(id);
	}

}
