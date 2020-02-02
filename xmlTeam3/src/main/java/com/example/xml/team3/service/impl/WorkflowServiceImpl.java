package com.example.xml.team3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xml.team3.model.workflow.Workflow;
import com.example.xml.team3.repository.WorkflowRepository;
import com.example.xml.team3.service.WorkflowService;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowRepository workflowRepository;

	public String createNewWorkflow(Workflow workflow) throws Exception {
		return workflowRepository.save(workflow);
	}

	public Workflow findById(String id) throws Exception {
		return workflowRepository.findById(id);
	}

	public String updateWorkflow(String id, Workflow workflow) throws Exception {
		return workflowRepository.update(id, workflow);
	}

	public boolean deleteWorkflow(String id) throws Exception {
		return workflowRepository.delete(id);
	}

}
