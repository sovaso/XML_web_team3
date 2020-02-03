package com.example.xml.team3.service;

import com.example.xml.team3.model.coverletter.CoverLetter;

public interface CoverLetterService {

	public String createNewCoverLetter(CoverLetter coverLetter) throws Exception;

	public CoverLetter findById(String id) throws Exception;

	public String updateCoverLetter(String id, CoverLetter coverLetter) throws Exception;

	public boolean deleteCoverLetter(String id) throws Exception;
	
	public CoverLetter findByScientificWorkId(String id) throws Exception;
}
