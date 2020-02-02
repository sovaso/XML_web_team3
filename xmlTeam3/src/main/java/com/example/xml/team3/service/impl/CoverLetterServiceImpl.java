package com.example.xml.team3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.coverletter.CoverLetter;
import com.example.xml.team3.repository.CoverLetterRepository;
import com.example.xml.team3.service.CoverLetterService;

@Service
public class CoverLetterServiceImpl implements CoverLetterService {

	@Autowired
	private CoverLetterRepository coverLetterRepository;

	public String createNewCoverLetter(CoverLetter coverLetter) throws Exception {
		return coverLetterRepository.save(coverLetter);
	}

	public CoverLetter findById(String id) throws Exception {
		return coverLetterRepository.findById(id);
	}

	public String updateCoverLetter(String id, CoverLetter coverLetter) throws Exception {
		return coverLetterRepository.update(id, coverLetter);
	}

	public boolean deleteCoverLetter(String id) throws Exception {
		return coverLetterRepository.delete(id);
	}

}
