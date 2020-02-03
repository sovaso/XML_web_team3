package com.example.xml.team3.controller;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.CoverLetterDTO;
import com.example.xml.team3.model.coverletter.CoverLetter;
import com.example.xml.team3.service.CoverLetterService;

@RestController
@RequestMapping(value = "/coverLetter")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CoverLetterController {

	@Autowired
	CoverLetterService coverLetterService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createCoverLetter(@RequestBody CoverLetterDTO coverLetterDTO) {

		CoverLetter coverLetter = new CoverLetter();
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coverLetter.setDate(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
		coverLetter.setText(coverLetterDTO.getText());
		coverLetter.setScientificWorkId(coverLetterDTO.getScientificWorkId());
		String id = "";
		try {
			id = coverLetterService.createNewCoverLetter(coverLetter);
			return new ResponseEntity<String>(id, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");
			return new ResponseEntity<String>(id, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getById")
	public ResponseEntity<CoverLetterDTO> getById(@RequestBody String coverLetterId) {
		CoverLetter coverLetter = new CoverLetter();
		CoverLetterDTO retVal = null;
		try {
			coverLetter = coverLetterService.findById(coverLetterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (coverLetter == null) {
			return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.NOT_FOUND);
		}
		String textDTO = coverLetter.getText();
		String idDTO = coverLetter.getId();
		String scientificWorkIdDTO = coverLetter.getScientificWorkId();
		// ubacivanje u listu
		retVal = new CoverLetterDTO(idDTO, scientificWorkIdDTO, textDTO);
		return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getByScientificWorkId")
	public ResponseEntity<CoverLetterDTO> getByScientificWorkId(@RequestBody String scientificWorkId) {
		CoverLetter coverLetter = new CoverLetter();
		CoverLetterDTO retVal = null;
		try {
			coverLetter = coverLetterService.findByScientificWorkId(scientificWorkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (coverLetter == null) {
			return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.NOT_FOUND);
		}
		String textDTO = coverLetter.getText();
		String idDTO = coverLetter.getId();
		String scientificWorkIdDTO = coverLetter.getScientificWorkId();
		// ubacivanje u listu
		retVal = new CoverLetterDTO(idDTO, scientificWorkIdDTO, textDTO);
		return new ResponseEntity<CoverLetterDTO>(retVal, HttpStatus.OK);
	}

}
