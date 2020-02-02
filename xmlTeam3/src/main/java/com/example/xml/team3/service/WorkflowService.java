package com.example.xml.team3.service;

import com.example.xml.team3.model.workflow.Workflow;

public interface WorkflowService {

	public String createNewWorkflow(Workflow workflow) throws Exception;

	public Workflow findById(String id) throws Exception;

	public String updateWorkflow(String id, Workflow workflow) throws Exception;

	public boolean deleteWorkflow(String id) throws Exception;
}
