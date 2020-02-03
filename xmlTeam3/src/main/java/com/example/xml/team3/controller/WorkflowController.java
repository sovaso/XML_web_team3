package com.example.xml.team3.controller;

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

import com.example.xml.team3.dto.WorkflowDTO;
import com.example.xml.team3.model.workflow.Workflow;
import com.example.xml.team3.service.WorkflowService;

@RestController
@RequestMapping(value = "/workflow")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class WorkflowController {

	@Autowired
	WorkflowService workflowService;

	@PostMapping(value = "/create")
	public ResponseEntity<Boolean> createWorkflow(@RequestBody WorkflowDTO workflowDTO) {

		System.out.println("CREATE WORKFLOW CALLED");
		Workflow workflow = new Workflow();
		workflow.setAuthorUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		workflow.setEditorUsername("");
		workflow.setReviewerUsername("");
		workflow.setScientificWorkId(workflowDTO.getScientificWorkId());
		try {
			workflowService.createNewWorkflow(workflow);
			return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("Uhvacen exception, treba da se vrati false");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/getById")
	public ResponseEntity<WorkflowDTO> getById(@RequestBody String workflowId) {
		Workflow workflow = new Workflow();
		WorkflowDTO retVal = null;
		try {
			workflow = workflowService.findById(workflowId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (workflow == null) {
			return new ResponseEntity<WorkflowDTO>(retVal, HttpStatus.NOT_FOUND);
		}
		String authorUsernameDTO = workflow.getAuthorUsername();
		String reviewerUsernameDTO = workflow.getReviewerUsername();
		String editorUsernameDTO = workflow.getEditorUsername();
		String scientificWorkIdDTO = workflow.getScientificWorkId();
		String idDTO = workflow.getId();
		// ubacivanje u listu
		retVal = new WorkflowDTO(authorUsernameDTO, reviewerUsernameDTO, editorUsernameDTO, scientificWorkIdDTO, idDTO);
		return new ResponseEntity<WorkflowDTO>(retVal, HttpStatus.OK);
	}
}
