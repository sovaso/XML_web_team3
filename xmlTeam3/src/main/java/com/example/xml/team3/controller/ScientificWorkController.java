package com.example.xml.team3.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.AbstractDTO;
import com.example.xml.team3.dto.AuthorDTO;
import com.example.xml.team3.dto.HeaderDTO;
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
	public ResponseEntity<?> createScientificWork(@RequestBody @Valid ScientificWorkDTO scientificWorkDTO) {
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
			a.setName(autDTO.getName());
			a.setSurname(autDTO.getSurname());
			a.getUniversity().setAddress(autDTO.getUniversityAddress());
			a.getUniversity().setName(autDTO.getUniversityName());
			a.setUsername(scientificWorkService.getUsernameByNameAndSurname(autDTO.getName(), autDTO.getSurname()));
			retVal.getAuthors().getAuthor().add(a);
		}
		// status
		retVal.setStatus(StatusType.SUBMITTED);
		// header
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

	@GetMapping(value = "/getById")
	public ResponseEntity<ScientificWorkDTO> getById(@RequestBody String scientificWorkId) {
		ScientificWork scientificWork = new ScientificWork();
		ScientificWorkDTO retVal = null;
		try {
			scientificWork = scientificWorkService.findById(scientificWorkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (scientificWork == null) {
			return new ResponseEntity<ScientificWorkDTO>(retVal, HttpStatus.NOT_FOUND);
		}
		// paragraf
		List<String> paragraphsDTO = new ArrayList<String>();
		for (Paragraph p : scientificWork.getParagraph()) {
			paragraphsDTO.add(p.getText());
		}
		// heder
		HeaderDTO headerDTO = new HeaderDTO("", "", "");
		// title
		String titleDTO = scientificWork.getTitle();
		// autori
		List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
		for (Author author : scientificWork.getAuthors().getAuthor()) {
			authorsDTO.add(new AuthorDTO(author.getName(), author.getSurname(), author.getUniversity().getName(),
					author.getUniversity().getAddress()));
		}
		// apstrakt
		AbstractDTO abstractDTO = new AbstractDTO(scientificWork.getAbstract().getPurpose(),
				scientificWork.getAbstract().getDesign(), scientificWork.getAbstract().getFindings(),
				scientificWork.getAbstract().getLimitations(), scientificWork.getAbstract().getOriginality(),
				scientificWork.getAbstract().getScientificWorkType(),
				scientificWork.getAbstract().getKeywords().getKeyword());
		// reference
		List<ReferenceDTO> referenceDTO = new ArrayList<ReferenceDTO>();
		for (Reference r : scientificWork.getReferences().getReference()) {
			referenceDTO.add(new ReferenceDTO(r.getValue(), r.getScientificWorkId(), r.getId()));
		}
		// komentari
		List<String> commentsDTO = new ArrayList<String>();
		commentsDTO.addAll(scientificWork.getComment());
		// ubacivanje u listu
		retVal = new ScientificWorkDTO(headerDTO, titleDTO, authorsDTO, abstractDTO, paragraphsDTO, referenceDTO,
				commentsDTO);
		return new ResponseEntity<ScientificWorkDTO>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllPublished")
	public ResponseEntity<List<ScientificWorkDTO>> findAllPublished() {
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		List<ScientificWork> allPublished = scientificWorkService.findAllPublished();
		for (ScientificWork scientificWork : allPublished) {
			// paragraf
			List<String> paragraphsDTO = new ArrayList<String>();
			for (Paragraph p : scientificWork.getParagraph()) {
				paragraphsDTO.add(p.getText());
			}
			// heder
			HeaderDTO headerDTO = new HeaderDTO("", "", "");
			// title
			String titleDTO = scientificWork.getTitle();
			// autori
			List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
			for (Author author : scientificWork.getAuthors().getAuthor()) {
				authorsDTO.add(new AuthorDTO(author.getName(), author.getSurname(), author.getUniversity().getName(),
						author.getUniversity().getAddress()));
			}
			// apstrakt
			AbstractDTO abstractDTO = new AbstractDTO(scientificWork.getAbstract().getPurpose(),
					scientificWork.getAbstract().getDesign(), scientificWork.getAbstract().getFindings(),
					scientificWork.getAbstract().getLimitations(), scientificWork.getAbstract().getOriginality(),
					scientificWork.getAbstract().getScientificWorkType(),
					scientificWork.getAbstract().getKeywords().getKeyword());
			// reference
			List<ReferenceDTO> referenceDTO = new ArrayList<ReferenceDTO>();
			for (Reference r : scientificWork.getReferences().getReference()) {
				referenceDTO.add(new ReferenceDTO(r.getValue(), r.getScientificWorkId(), r.getId()));
			}
			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu
			retVal.add(new ScientificWorkDTO(headerDTO, titleDTO, authorsDTO, abstractDTO, paragraphsDTO, referenceDTO,
					commentsDTO));
		}
		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllForConcreteUser")
	public ResponseEntity<List<ScientificWorkDTO>> findAllForConcreteUser() {
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		String usernameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ScientificWork> allForConcrete = scientificWorkService.findAllForConcreteUser(usernameCurrentUser);
		for (ScientificWork scientificWork : allForConcrete) {
			// paragraf
			List<String> paragraphsDTO = new ArrayList<String>();
			for (Paragraph p : scientificWork.getParagraph()) {
				paragraphsDTO.add(p.getText());
			}
			// heder
			HeaderDTO headerDTO = new HeaderDTO("", "", "");
			// title
			String titleDTO = scientificWork.getTitle();
			// autori
			List<AuthorDTO> authorsDTO = new ArrayList<AuthorDTO>();
			for (Author author : scientificWork.getAuthors().getAuthor()) {
				authorsDTO.add(new AuthorDTO(author.getName(), author.getSurname(), author.getUniversity().getName(),
						author.getUniversity().getAddress()));
			}
			// apstrakt
			AbstractDTO abstractDTO = new AbstractDTO(scientificWork.getAbstract().getPurpose(),
					scientificWork.getAbstract().getDesign(), scientificWork.getAbstract().getFindings(),
					scientificWork.getAbstract().getLimitations(), scientificWork.getAbstract().getOriginality(),
					scientificWork.getAbstract().getScientificWorkType(),
					scientificWork.getAbstract().getKeywords().getKeyword());
			// reference
			List<ReferenceDTO> referenceDTO = new ArrayList<ReferenceDTO>();
			for (Reference r : scientificWork.getReferences().getReference()) {
				referenceDTO.add(new ReferenceDTO(r.getValue(), r.getScientificWorkId(), r.getId()));
			}
			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu
			retVal.add(new ScientificWorkDTO(headerDTO, titleDTO, authorsDTO, abstractDTO, paragraphsDTO, referenceDTO,
					commentsDTO));
		}
		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

}
