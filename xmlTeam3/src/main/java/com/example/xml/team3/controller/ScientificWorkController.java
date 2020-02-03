package com.example.xml.team3.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.AuthorDTO;
import com.example.xml.team3.dto.ReferenceDTO;
import com.example.xml.team3.dto.ScientificWorkDTO;
import com.example.xml.team3.model.scientificwork.Author;
import com.example.xml.team3.model.scientificwork.Paragraph;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.scientificwork.ScientificWork.References.Reference;
import com.example.xml.team3.model.scientificwork.StatusType;
import com.example.xml.team3.service.ScientificWorkService;

@RestController
@RequestMapping(value = "/scientificWork")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ScientificWorkController {

	@Autowired
	ScientificWorkService scientificWorkService;

	@PostMapping(value = "/create")
	public ResponseEntity<?> createScientificWork(@RequestBody ScientificWorkDTO scientificWorkDTO) {
		System.out.println("Uslo u create scientific work");
		
		ScientificWork retVal = new ScientificWork();
		// title
		retVal.setTitle(scientificWorkDTO.getTitle());
		// paragraf
		for (String s : scientificWorkDTO.getParagraphs()) {
			Paragraph p = new Paragraph();
			p.setText(s);
			retVal.getParagraph().add(p);
		}
		// komentari
		retVal.getComment().addAll(scientificWorkDTO.getComments());
		// reference
		
		for (ReferenceDTO rfDTO : scientificWorkDTO.getReferenceDTO()) {
			Reference rf = new Reference();
			rf.setValue(rfDTO.getValue());
			retVal.getReferences().getReference().add(rf);
		}
		
		// apstrakt
		ScientificWork.Abstract abstracct = new ScientificWork.Abstract();
		ScientificWork.Abstract.Keywords keywords = new ScientificWork.Abstract.Keywords();
		
		retVal.setAbstract(abstracct);
		retVal.getAbstract().setKeywords(keywords);
		scientificWorkDTO.getAbstractDTO();
		System.out.println(scientificWorkDTO.getAbstractDTO().getFindings());
		System.out.println(scientificWorkDTO.getAbstractDTO().getDesign());
		retVal.getAbstract().setDesign(scientificWorkDTO.getAbstractDTO().getDesign());
		retVal.getAbstract().setFindings(scientificWorkDTO.getAbstractDTO().getFindings());
		retVal.getAbstract().setLimitations(scientificWorkDTO.getAbstractDTO().getLimitations());
		retVal.getAbstract().setOriginality(scientificWorkDTO.getAbstractDTO().getOriginality());
		retVal.getAbstract().setPurpose(scientificWorkDTO.getAbstractDTO().getPurpose());
		retVal.getAbstract().setScientificWorkType(scientificWorkDTO.getAbstractDTO().getType());
		retVal.getAbstract().getKeywords().getKeyword().addAll(scientificWorkDTO.getAbstractDTO().getKeywords());
		// autori
		for (AuthorDTO autDTO : scientificWorkDTO.getAuthorsDTO()) {
			Author a = new Author();
			a.setUniversity(new Author.University());
			a.setName(autDTO.getName());
			a.getUniversity().setAddress(autDTO.getUniversityAddress());
			a.getUniversity().setName(autDTO.getUniversityName());
			// username treba srediti!!!
			a.setUsername("vasa");
			ScientificWork.Authors authors = new ScientificWork.Authors();
			retVal.setAuthors(authors);
			retVal.getAuthors().getAuthor().add(a);
		}
		// status
		retVal.setStatus(StatusType.SUBMITTED);
		// header
		retVal.setHeader(new ScientificWork.Header());
		retVal.getHeader().setAccepted(null);
		retVal.getHeader().setRevised(null);
		retVal.getHeader().setReceived(null);
		try {
			scientificWorkService.createNewScientificWork(retVal);
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getAllPublished")
	public ResponseEntity<List<ScientificWork>> findAllPublished() {
		List<ScientificWork> retVal = scientificWorkService.findAllPublished();
		return new ResponseEntity<List<ScientificWork>>(retVal, HttpStatus.OK);
	}

}
