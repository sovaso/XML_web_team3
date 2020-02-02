package com.example.xml.team3.service;

import com.example.xml.team3.model.scientificwork.ScientificWork;

public interface ScientificWorkService {

	public String createNewScientificWork(ScientificWork scientificWork) throws Exception;

	public ScientificWork findById(String id) throws Exception;

	public String updateScientificWork(String id, ScientificWork scientificWork) throws Exception;

	public boolean deleteScientificWork(String id) throws Exception;
}
