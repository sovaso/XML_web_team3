package com.example.xml.team3.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.AbstractDTO;
import com.example.xml.team3.dto.AuthorDTO;
import com.example.xml.team3.dto.HeaderDTO;
import com.example.xml.team3.dto.IdDTO;
import com.example.xml.team3.dto.PickedReviewerDTO;
import com.example.xml.team3.dto.ReferenceDTO;
import com.example.xml.team3.dto.ScientificWorkDTO;
import com.example.xml.team3.dto.SearchDTO;
import com.example.xml.team3.model.scientificwork.Author;
import com.example.xml.team3.model.scientificwork.Paragraph;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.scientificwork.ScientificWork.References;
import com.example.xml.team3.model.scientificwork.StatusType;
import com.example.xml.team3.model.workflow.Workflow;
import com.example.xml.team3.service.MailService;
import com.example.xml.team3.service.ReviewService;
import com.example.xml.team3.service.ScientificWorkService;
import com.example.xml.team3.service.UserService;
import com.example.xml.team3.service.WorkflowService;
import com.example.xml.team3.util.XsltUtil;
import com.example.xml.team3.util.Transformer.XSLFOTransformer;
import com.example.xml.team3.util.jaxb.MarshallerUtil;

@RestController
@RequestMapping(value = "/scientificWork")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ScientificWorkController {

	@Autowired
	ScientificWorkService scientificWorkService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	XsltUtil xsltUtil;

	@Autowired
	XSLFOTransformer xslfoTransformer;

	@Autowired
	MarshallerUtil marshallerUtil;

	@Autowired
	MailService mailService;

	@Autowired
	UserService userService;

	private final String scientificWorkXsdPath = "src/main/resources/xsd/scientificWork.xsd";
	private final String scientificWorkXslPath = "src/main/resources/xsl/scientificWork.xsl";

	@PostMapping(value = "/create")
	public ResponseEntity<IdDTO> createScientificWork(@RequestBody ScientificWorkDTO scientificWorkDTO) {
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

		System.out.println("Broj referenciiiii");
		System.out.println(scientificWorkDTO.getReferenceDTO().size());
		for (ReferenceDTO rfDTO : scientificWorkDTO.getReferenceDTO()) {
			ScientificWork.References rf = new ScientificWork.References();
			rf.setScientificWorkId(rfDTO.getScientificWorkId());
			retVal.getReferences().add(rf);
		}

		System.out.println("Retval broj referenci");
		System.out.println(retVal.getReferences().size());
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
		ScientificWork.Authors authors = new ScientificWork.Authors();
		for (AuthorDTO autDTO : scientificWorkDTO.getAuthorsDTO()) {
			Author a = new Author();
			a.setUniversity(new Author.University());
			a.setName(autDTO.getName());
			a.setSurname(autDTO.getSurname());
			a.getUniversity().setAddress(autDTO.getUniversityAddress());
			a.getUniversity().setName(autDTO.getUniversityName());
			a.setUsername(scientificWorkService.getUsernameByNameAndSurname(autDTO.getName(), autDTO.getSurname()));
			authors.getAuthor().add(a);
		}
		retVal.setAuthors(authors);
		// status
		retVal.setStatus(StatusType.SUBMITTED);
		// retVal.setStatus(StatusType.ACCEPTED);
		// header
		retVal.setHeader(new ScientificWork.Header());
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retVal.getHeader().setAccepted(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
		retVal.getHeader().setReceived(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
		retVal.getHeader().setRevised(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
		IdDTO idDto = new IdDTO();
		idDto.setResponse("");
		String id = "";
		try {
			id = scientificWorkService.createNewScientificWork(retVal);
			idDto.setResponse(id);
			// System.out.println("ID ============= "+id);
			return new ResponseEntity<IdDTO>(idDto, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");

			return new ResponseEntity<IdDTO>(idDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/revisingScientificWork")
	public ResponseEntity<IdDTO> revisingScientificWork(@RequestBody ScientificWorkDTO scientificWorkDTO)
			throws Exception {

		ScientificWork retVal = null;
		try {
			retVal = scientificWorkService.findById(scientificWorkDTO.getScientificWorkId());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// title
		if (retVal == null) {
			return new ResponseEntity<IdDTO>(new IdDTO("-1"), HttpStatus.OK);
		}
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
			References rf = new References();
			rf.setScientificWorkId(rfDTO.getScientificWorkId());
			retVal.getReferences().add(rf);
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
			a.setSurname(autDTO.getSurname());
			a.getUniversity().setAddress(autDTO.getUniversityAddress());
			a.getUniversity().setName(autDTO.getUniversityName());
			ScientificWork.Authors authors = new ScientificWork.Authors();
			retVal.setAuthors(authors);
			a.setUsername(scientificWorkService.getUsernameByNameAndSurname(autDTO.getName(), autDTO.getSurname()));
			retVal.getAuthors().getAuthor().add(a);
		}
		// status
		retVal.setStatus(StatusType.REVIEWING);
		// retVal.setStatus(StatusType.ACCEPTED);
		// header
		retVal.setHeader(new ScientificWork.Header());
		retVal.getHeader().setAccepted(null);
		retVal.getHeader().setRevised(null);
		retVal.getHeader().setReceived(null);
		IdDTO idDto = new IdDTO();
		idDto.setResponse("");
		String id = "";
		// slanje mejla
		String workflowId = reviewService.getWorkflowIdByScientificWorkId(retVal.getId());
		Workflow w = workflowService.findById(workflowId);
		String swXML = marshallerUtil.marshallScientificWork(retVal);
		String receiverMail = userService.getEmailByUsername(w.getReviewerUsername());
		String senderMail = userService.getEmailByUsername(w.getAuthorUsername());
		String subject = "Scientific work has been revised";
		String text = "My scientific work \"" + retVal.getTitle() + "\" has just been revised!";
		mailService.sendMailNotification(scientificWorkXsdPath, scientificWorkXslPath, swXML, senderMail, receiverMail,
				subject, text);
		try {
			id = scientificWorkService.updateScientificWork(scientificWorkDTO.getScientificWorkId(), retVal);
			idDto.setResponse(id);
			// System.out.println("ID ============= "+id);
			return new ResponseEntity<IdDTO>(idDto, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");

			return new ResponseEntity<IdDTO>(idDto, HttpStatus.INTERNAL_SERVER_ERROR);
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
		for (References r : scientificWork.getReferences()) {
			referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
		}
		// komentari
		List<String> commentsDTO = new ArrayList<String>();
		commentsDTO.addAll(scientificWork.getComment());
		// ubacivanje u listu

		retVal = new ScientificWorkDTO(null, headerDTO, titleDTO, authorsDTO, abstractDTO, paragraphsDTO, referenceDTO,
				commentsDTO, scientificWork.getStatus().toString().toLowerCase());

		return new ResponseEntity<ScientificWorkDTO>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllPublished")
	public ResponseEntity<List<ScientificWorkDTO>> findAllPublished() {
		System.out.println("Uslo u findAllPublished!!! :)");
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		List<ScientificWork> allPublished = scientificWorkService.findAllPublished();
		System.out.println("Velicina od find all published: " + allPublished.size());
		for (ScientificWork scientificWork : allPublished) {
			System.out.println(allPublished.get(0).getTitle());
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
			ScientificWork.References references = new ScientificWork.References();
			scientificWork.getReferences().add(references);
			for (References r : scientificWork.getReferences()) {
				referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
			}
			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			System.out.println("SCIENTIFIC WORK ID");
			System.out.println(scientificWork.getId());
			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));

		}
		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllForConcreteUser")
	public ResponseEntity<List<ScientificWorkDTO>> findAllForConcreteUser() {
		System.out.println("Uslo u find all for concrete user");
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Find my works - dto size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	// PDF I HTML I XML =========

	@GetMapping(value = "/getByIdXML/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<IdDTO> getXML(@PathVariable String id) throws Exception {
		ScientificWork scientificWork = scientificWorkService.findById(id);
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		return new ResponseEntity<>(new IdDTO(scientificWorkXML), HttpStatus.OK);
	}

	@GetMapping(value = "/getByIdHTML/{id}")
	public ResponseEntity<IdDTO> getHTML(@PathVariable String id) throws Exception {
		ScientificWork scientificWork = scientificWorkService.findById(id);
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		IdDTO idDto = new IdDTO();
		String swHtml = xslfoTransformer.generateHTML(scientificWorkXML, scientificWorkXslPath);
		idDto.setResponse(swHtml);
		return new ResponseEntity<>(idDto, HttpStatus.OK);
	}

	@GetMapping(value = "/getByIdPDF/{id}", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<ByteArrayResource> getPDF(@PathVariable String id) throws Exception {
		ScientificWork scientificWork = scientificWorkService.findById(id);
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		return new ResponseEntity<>(
				new ByteArrayResource(IOUtils.toByteArray(XsltUtil
						.toPdf(scientificWorkXML, scientificWorkXslPath, scientificWorkXsdPath).getInputStream())),
				HttpStatus.OK);
	}

	// PDF I HTML =========

	@GetMapping(value = "/withdrawScientificWork/{scientificWorkId}")
	public ResponseEntity<Boolean> withdrawScientificWork(@PathVariable String scientificWorkId) throws Exception {
		System.out.println("Uslo u withdraw scientific work");
		ScientificWork sw = scientificWorkService.findById(scientificWorkId);
		if (sw == null) {
			System.out.println("Scientific work nije nadjen.");
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		sw.setStatus(StatusType.WITHDRAWN);
		scientificWorkService.updateScientificWork(scientificWorkId, sw);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PutMapping(value = "/rejectScientificWork")
	public ResponseEntity<Boolean> rejectScientificWork(@RequestBody String scientificWorkId) throws Exception {
		ScientificWork sw = scientificWorkService.findById(scientificWorkId);
		if (sw == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		sw.setStatus(StatusType.REJECTED);
		// slanje mejla
		String workflowId = reviewService.getWorkflowIdByScientificWorkId(scientificWorkId);
		Workflow w = workflowService.findById(workflowId);
		String swXML = marshallerUtil.marshallScientificWork(sw);
		String receiverMail = userService.getEmailByUsername(w.getAuthorUsername());
		String subject = "Scientific work rejected";
		String text = "Your scientific work \"" + sw.getTitle() + "\" has been rejected!";
		mailService.sendMailNotification(scientificWorkXsdPath, scientificWorkXslPath, swXML,
				"marina.vojnovic1997@gmail.com", receiverMail, subject, text);
		scientificWorkService.updateScientificWork(scientificWorkId, sw);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PutMapping(value = "/acceptScientificWork")
	public ResponseEntity<Boolean> acceptScientificWork(@RequestBody String scientificWorkId) throws Exception {
		ScientificWork sw = scientificWorkService.findById(scientificWorkId);
		if (sw == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		sw.setStatus(StatusType.ACCEPTED);
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = null;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sw.getHeader().setAccepted(datatypeFactory.newXMLGregorianCalendar(gregorianCalendar));
		// slanje mejla
		String workflowId = reviewService.getWorkflowIdByScientificWorkId(scientificWorkId);
		Workflow w = workflowService.findById(workflowId);
		String swXML = marshallerUtil.marshallScientificWork(sw);
		String senderMail = userService.getEmailByUsername(w.getReviewerUsername());
		String receiverMail = userService.getEmailByUsername(w.getAuthorUsername());
		String subject = "Scientific work accepted";
		String text = "Your scientific work \"" + sw.getTitle() + "\" has been accepted and will be published!";
		mailService.sendMailNotification(scientificWorkXsdPath, scientificWorkXslPath, swXML, senderMail, receiverMail,
				subject, text);
		scientificWorkService.updateScientificWork(scientificWorkId, sw);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllForRevision")
	public ResponseEntity<List<ScientificWorkDTO>> findAllForRevision() {
		System.out.println("Uslo u find all for revision");
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		String usernameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ScientificWork> allForRevision = scientificWorkService.findAllForRevision(usernameCurrentUser);
		for (ScientificWork scientificWork : allForRevision) {
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Find my works - dto size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllSubmitted")
	public ResponseEntity<List<ScientificWorkDTO>> findAllSubmitted() {
		System.out.println("Uslo u find all submitted");
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		List<ScientificWork> allSubmitted = scientificWorkService.findAllSubmitted();
		for (ScientificWork scientificWork : allSubmitted) {
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Find my works - dto size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllForReviewing")
	public ResponseEntity<List<ScientificWorkDTO>> findAllForReviewing() {
		System.out.println("Uslo u find all for reviewing");
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		String usernameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ScientificWork> allForReviewing = scientificWorkService.findAllForReviewing(usernameCurrentUser);
		for (ScientificWork scientificWork : allForReviewing) {
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Find my works - dto size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	@PostMapping(value = "/pickedReviewer")
	public ResponseEntity<Boolean> pickedReviewer(@RequestBody PickedReviewerDTO pickedReviewerDTO) throws Exception {
		String workflowId = reviewService.getWorkflowIdByScientificWorkId(pickedReviewerDTO.getScientificWorkId());
		Workflow w = workflowService.findById(workflowId);
		w.setEditorUsername(pickedReviewerDTO.getEditorUsername());
		w.setReviewerUsername(pickedReviewerDTO.getReviewerUsername());
		workflowService.updateWorkflow(workflowId, w);
		String scientificWorkId = pickedReviewerDTO.getScientificWorkId();
		ScientificWork sw = scientificWorkService.findById(scientificWorkId);
		if (sw == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		sw.setStatus(StatusType.REVIEWING);
		// slanje mejla
		String swXML = marshallerUtil.marshallScientificWork(sw);
		String senderMail = userService.getEmailByUsername(w.getEditorUsername());
		String receiverMail = userService.getEmailByUsername(w.getReviewerUsername());
		String subject = "Scientific work reviewing";
		String text = "Scientific work \"" + sw.getTitle() + "\" has been granted to you to review!";
		mailService.sendMailNotification(scientificWorkXsdPath, scientificWorkXslPath, swXML, senderMail, receiverMail,
				subject, text);
		scientificWorkService.updateScientificWork(scientificWorkId, sw);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PostMapping(value = "/searchMyWorks")
	public ResponseEntity<List<ScientificWorkDTO>> searchMyWorks(@RequestBody SearchDTO searchDTO) {
		System.out.println("Uslo u pretragu search my works");
		List<ScientificWorkDTO> retVal = new ArrayList<>();

		String usernameCurrentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		List<ScientificWork> search = scientificWorkService.searchScientificWork(searchDTO, usernameCurrentUser);
		for (ScientificWork scientificWork : search) {
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Find my works - dto size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

	/*
	 * @PostMapping(value = "/searchAuthorized") public
	 * ResponseEntity<List<ScientificWorkDTO>> searchAuthorized(@RequestBody
	 * SearchDTO searchDTO) { System.out.println("Uslo u pretragu kao registrovan");
	 * List<ScientificWorkDTO> retVal = new ArrayList<>(); String
	 * usernameCurrentUser =
	 * SecurityContextHolder.getContext().getAuthentication().getName();
	 * List<ScientificWork> search =
	 * scientificWorkService.searchScientificWork(searchDTO, usernameCurrentUser);
	 * for (ScientificWork scientificWork : search) { // paragraf List<String>
	 * paragraphsDTO = new ArrayList<String>(); for (Paragraph p :
	 * scientificWork.getParagraph()) { paragraphsDTO.add(p.getText()); } // heder
	 * HeaderDTO headerDTO = new HeaderDTO("", "", ""); // title String titleDTO =
	 * scientificWork.getTitle(); // autori List<AuthorDTO> authorsDTO = new
	 * ArrayList<AuthorDTO>(); for (Author author :
	 * scientificWork.getAuthors().getAuthor()) { authorsDTO.add(new
	 * AuthorDTO(author.getName(), author.getSurname(),
	 * author.getUniversity().getName(), author.getUniversity().getAddress())); } //
	 * apstrakt AbstractDTO abstractDTO = new
	 * AbstractDTO(scientificWork.getAbstract().getPurpose(),
	 * scientificWork.getAbstract().getDesign(),
	 * scientificWork.getAbstract().getFindings(),
	 * scientificWork.getAbstract().getLimitations(),
	 * scientificWork.getAbstract().getOriginality(),
	 * scientificWork.getAbstract().getScientificWorkType(),
	 * scientificWork.getAbstract().getKeywords().getKeyword()); // reference
	 * List<ReferenceDTO> referenceDTO = new ArrayList<ReferenceDTO>(); if
	 * (scientificWork.getReferences() != null) { for (References r :
	 * scientificWork.getReferences()) { referenceDTO.add(new
	 * ReferenceDTO(r.getScientificWorkId())); } }
	 * 
	 * // komentari List<String> commentsDTO = new ArrayList<String>();
	 * commentsDTO.addAll(scientificWork.getComment()); // ubacivanje u listu
	 * 
	 * retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO,
	 * authorsDTO, abstractDTO, paragraphsDTO, referenceDTO, commentsDTO,
	 * scientificWork.getStatus().toString().toLowerCase())); }
	 * System.out.println("Find my works - dto size: " + retVal.size());
	 * 
	 * return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK); }
	 */
	@PostMapping(value = "/searchUnauthorized")
	public ResponseEntity<List<ScientificWorkDTO>> searchUnauthorized(@RequestBody SearchDTO searchDTO) {
		System.out.println("Uslo u pretragu kao neregistrovan");
		List<ScientificWorkDTO> retVal = new ArrayList<>();
		// String usernameCurrentUser =
		// SecurityContextHolder.getContext().getAuthentication().getName();
		List<ScientificWork> search = scientificWorkService.searchScientificWork(searchDTO, "");
		for (ScientificWork scientificWork : search) {
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
			if (scientificWork.getReferences() != null) {
				for (References r : scientificWork.getReferences()) {
					referenceDTO.add(new ReferenceDTO(r.getScientificWorkId()));
				}
			}

			// komentari
			List<String> commentsDTO = new ArrayList<String>();
			commentsDTO.addAll(scientificWork.getComment());
			// ubacivanje u listu

			retVal.add(new ScientificWorkDTO(scientificWork.getId(), headerDTO, titleDTO, authorsDTO, abstractDTO,
					paragraphsDTO, referenceDTO, commentsDTO, scientificWork.getStatus().toString().toLowerCase()));
		}
		System.out.println("Search result size: " + retVal.size());

		return new ResponseEntity<List<ScientificWorkDTO>>(retVal, HttpStatus.OK);
	}

}
