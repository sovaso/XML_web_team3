package com.example.xml.team3.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.ScientificWorkDTO;
import com.example.xml.team3.model.scientificwork.Paragraph;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.service.ScientificWorkService;

@RestController
@RequestMapping(value = "/scientificWork")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ScientificWorkController {

	@Autowired
	ScientificWorkService scientificWorkService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> createScientificWork(@RequestBody @Valid ScientificWorkDTO scientificWorkDTO) {
		ScientificWork retVal = new ScientificWork();
		retVal.setTitle(scientificWorkDTO.getTitle());
		//paragraf
		for (String s : scientificWorkDTO.getParagraphs()) {
			Paragraph p = new Paragraph();
			//p.getContent()
		}
		retVal.getComment().addAll(scientificWorkDTO.getComments());
		return null;
	}
}
