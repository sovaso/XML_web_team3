package com.example.xml.team3.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.repository.ScientificWorkRepository;
import com.example.xml.team3.repository.UserRepository;
import com.example.xml.team3.service.ScientificWorkService;
import com.example.xml.team3.util.Transformer.XSLFOTransformer;
import com.example.xml.team3.util.jaxb.MarshallerUtil;

@Service
public class ScientificWorkServiceImpl implements ScientificWorkService {

	@Autowired
	private ScientificWorkRepository scientificWorkRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MarshallerUtil marshallerUtil;

	@Autowired
	private XSLFOTransformer xslfoTransformer;

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

	public List<ScientificWork> findAllPublished() {
		return scientificWorkRepository.findAllPublished();
	}

	public String getUsernameByNameAndSurname(String name, String surname) {
		return userRepository.getUsernameByNameAndSurname(name, surname);
	}

	public List<ScientificWork> findAllForConcreteUser(String username) {
		return scientificWorkRepository.findAllForConcreteUser(username);
	}

	public String findByIdHTML(String id) throws Exception {
		ScientificWork scientificWork = scientificWorkRepository.findById(id);
		if (scientificWork == null) {
			throw new Exception(id);
		}
		String scientificWorkString = marshallerUtil.marshallScientificWork(scientificWork);
		String swHTML = xslfoTransformer.generateHTML(scientificWorkString, "src/main/resources/xsl/scientificWork.xsl");
		return swHTML;
	}

	public ByteArrayOutputStream findByIdPDF(String id) throws Exception {
		ScientificWork scientificWork = scientificWorkRepository.findById(id);
		if (scientificWork == null) {
			throw new Exception(id);
		}
		String scientificWorkString = marshallerUtil.marshallScientificWork(scientificWork);
		ByteArrayOutputStream swPDF = xslfoTransformer.generatePDF(scientificWorkString,
				"src/main/resources/data/xsl-fo/scientificArticle_fo.xsl"); // napravi xsl-fo fajl
		return swPDF;
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

}
