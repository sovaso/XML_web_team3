package com.example.xml.team3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.xml.team3.dto.CommentDTO;
import com.example.xml.team3.dto.GradeDTO;
import com.example.xml.team3.dto.ReviewDTO;
import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.model.review.Review.Comments;
import com.example.xml.team3.model.review.Review.Grades;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.scientificwork.StatusType;
import com.example.xml.team3.model.workflow.Workflow;
import com.example.xml.team3.service.MailService;
import com.example.xml.team3.service.ReviewService;
import com.example.xml.team3.service.ScientificWorkService;
import com.example.xml.team3.service.UserService;
import com.example.xml.team3.service.WorkflowService;
import com.example.xml.team3.util.jaxb.MarshallerUtil;

@RestController
@RequestMapping(value = "/review")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	ScientificWorkService scientificWorkService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	MarshallerUtil marshallerUtil;

	@Autowired
	UserService userService;

	@Autowired
	MailService mailService;

	private final String reviewXsdPath = "src/main/resources/xsd/review.xsd";
	private final String reviewXslPath = "src/main/resources/xsl/review.xsl";

	@PostMapping(value = "/sendReview")
	public ResponseEntity<Boolean> createReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
		System.out.println("*****************************************************************");
		System.out.println("Uslo u create review");
		Review review = new Review();
		// komentari
		List<Comments> comments = new ArrayList<Comments>();
		for (CommentDTO c : reviewDTO.getComments()) {
			Comments comment = new Comments();
			comment.setRefId(c.getRefId());
			comment.setValue(c.getValue());
			comments.add(comment);
		}
		review.getComments().addAll(comments);
		// ocjene
		Grades grades = new Grades();
		grades.setAbstract(reviewDTO.getGrades().getAbsract());
		grades.setConclusions(reviewDTO.getGrades().getConclusions());
		grades.setExperiments(reviewDTO.getGrades().getExperiments());
		grades.setFormality(reviewDTO.getGrades().getFormality());
		grades.setGeneralGrade(reviewDTO.getGrades().getGeneralGrade());
		grades.setHeadings(reviewDTO.getGrades().getHeadings());
		grades.setKeywords(reviewDTO.getGrades().getKeywords());
		grades.setLayout(reviewDTO.getGrades().getLayout());
		grades.setReferences(reviewDTO.getGrades().getReferences());
		grades.setUniqueness(reviewDTO.getGrades().getUniqueness());
		review.setGrades(grades);
		// workflowID
		review.setWorkflowId(reviewService.getWorkflowIdByScientificWorkId(reviewDTO.getScientificWorkId()));
		// summary komentar
		review.setSummaryComment(reviewDTO.getSummaryComment());
		String id = "";
		// updatovati scientificWOrk
		String scientificWorkId = reviewDTO.getScientificWorkId();
		ScientificWork sw = scientificWorkService.findById(scientificWorkId);
		sw.setStatus(StatusType.REVISING);
		// slanje mejla
		String workflowId = reviewService.getWorkflowIdByScientificWorkId(scientificWorkId);
		Workflow w = workflowService.findById(workflowId);
		String swXML = marshallerUtil.marshallScientificWork(sw);
		//String senderMail = userService.getEmailByUsername(w.getReviewerUsername());
		//String receiverMail = userService.getEmailByUsername(w.getAuthorUsername());
		String subject = "Scientific work need to be revised";
		String text = "Your scientific work \"" + sw.getTitle()
				+ "\" needs to be revised so we could decide should we accept it or not!";
		//mailService.sendMailNotification(reviewXsdPath, reviewXslPath, swXML, "marina.vojnovic1997@gmail.com", "vpantic10@gmail.com", subject, text);
		
		System.out.println("********************************************************");
		System.out.println("Scientific work id in review controller");
		System.out.println(scientificWorkId);
		System.out.println("Scientific work title from scientific work");
		System.out.println("********************************************************");
		try {
			scientificWorkService.updateScientificWork(scientificWorkId, sw);
		}catch(Exception e) {
			System.out.println("Uslo u catch - SKROZ VALIDAN IZUZETAK");
		}
		
		try {
			id = reviewService.createNewReview(review);
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getById")
	public ResponseEntity<ReviewDTO> getById(@RequestBody String reviewId) {
		Review review = new Review();
		ReviewDTO retVal = null;
		try {
			review = reviewService.findById(reviewId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (review == null) {
			return new ResponseEntity<ReviewDTO>(retVal, HttpStatus.NOT_FOUND);
		}
		String summaryCommentDTO = review.getSummaryComment();
		String idDTO = review.getId();
		String workflowIdDTO = review.getWorkflowId();
		GradeDTO gradeDTO = new GradeDTO(review.getGrades().getUniqueness(), review.getGrades().getKeywords(),
				review.getGrades().getAbstract(), review.getGrades().getConclusions(),
				review.getGrades().getExperiments(), review.getGrades().getLayout(), review.getGrades().getFormality(),
				review.getGrades().getGeneralGrade(), review.getGrades().getReferences(),
				review.getGrades().getHeadings());
		List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
		for (Comments c : review.getComments()) {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setRefId(c.getRefId());
			commentDTO.setValue(c.getValue());
			commentsDTO.add(commentDTO);
		}
		// ubacivanje u listu
		retVal = new ReviewDTO(commentsDTO, gradeDTO, workflowIdDTO, summaryCommentDTO, idDTO);
		return new ResponseEntity<ReviewDTO>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllByScientificWorkId")
	public ResponseEntity<List<ReviewDTO>> getAllByScientificWorkId(@RequestBody String scientificWorkId) {
		List<Review> reviewList = new ArrayList<Review>();
		List<ReviewDTO> retValList = new ArrayList<ReviewDTO>();
		try {
			reviewList = reviewService.getAllByScientificWorkId(scientificWorkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Review review : reviewList) {
			String summaryCommentDTO = review.getSummaryComment();
			String idDTO = review.getId();
			String workflowIdDTO = review.getWorkflowId();
			GradeDTO gradeDTO = new GradeDTO(review.getGrades().getUniqueness(), review.getGrades().getKeywords(),
					review.getGrades().getAbstract(), review.getGrades().getConclusions(),
					review.getGrades().getExperiments(), review.getGrades().getLayout(),
					review.getGrades().getFormality(), review.getGrades().getGeneralGrade(),
					review.getGrades().getReferences(), review.getGrades().getHeadings());
			List<CommentDTO> commentsDTO = new ArrayList<CommentDTO>();
			for (Comments c : review.getComments()) {
				CommentDTO commentDTO = new CommentDTO();
				commentDTO.setRefId(c.getRefId());
				commentDTO.setValue(c.getValue());
				commentsDTO.add(commentDTO);
			}
			// ubacivanje u listu
			retValList.add(new ReviewDTO(commentsDTO, gradeDTO, workflowIdDTO, summaryCommentDTO, idDTO));
		}
		return new ResponseEntity<List<ReviewDTO>>(retValList, HttpStatus.OK);
	}

}
