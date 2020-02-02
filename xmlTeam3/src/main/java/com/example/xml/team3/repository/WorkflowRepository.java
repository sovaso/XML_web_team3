package com.example.xml.team3.repository;

import java.io.StringWriter;
import java.util.UUID;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.xml.team3.model.workflow.Workflow;
import com.example.xml.team3.util.Exist.ExistRetrieve;
import com.example.xml.team3.util.Exist.ExistStore;
import com.example.xml.team3.util.Exist.ExistUpdate;
import com.example.xml.team3.util.RDF.RDFStore;
import com.example.xml.team3.util.RDF.RDFUpdate;
import com.example.xml.team3.util.Template.XUpdateTemplate;
import com.example.xml.team3.util.jaxb.MarshallerUtil;
import com.example.xml.team3.util.jaxb.UnmarshallerUtil;

@Repository
public class WorkflowRepository {

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	public static String workflowCollectionId = "/db/team3/workflow";
	public static String workflowSchemaPath = "src/main/resources/xsd/workflow.xsd";

	public String save(Workflow workflow) throws Exception {
		String Id = generateNewWorkflowId();
		String workflowXML = marshallerUtil.marshallWorkflow(workflow);
		ExistStore.store(workflowCollectionId, Id, workflowXML);
		return Id;
	}

	public String update(String id, Workflow workflow) throws Exception {
		Workflow oldWorkflow = this.findById(id);
		if (oldWorkflow == null) {
			throw new Exception("There is no cover letter with this id");
		}
		this.delete(id);
		String workflowXML = marshallerUtil.marshallWorkflow(workflow);
		ExistStore.store(workflowCollectionId, id, workflowXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(workflowCollectionId, id);
		if (mods == 0) {
			return false;
		}
		deleteMetadata(id);
		return true;
	}

	public Workflow findById(String id) throws Exception {
		String xQuery = "//workflow[@id=\"" + id + "\"" + "]";
		Workflow retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(workflowCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/workflow");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallWorkflow(((XMLResource) res).getContent().toString());
					break;
				} finally {
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retVal;
	}

	public void saveMetadata(StringWriter metadata, String id) throws Exception {
		RDFStore.store(metadata, "/example/workflow/" + id);
	}

	public void deleteMetadata(String cvId) throws Exception {
		RDFUpdate.remove("/example/workflow/" + cvId);
	}

	public void updateMetadata(StringWriter metadata, String id) throws Exception {
		String url = "/example/workflow/" + id;
		deleteMetadata(id);
		RDFStore.store(metadata, url);
	}

	public String generateNewWorkflowId() throws Exception {
		String retVal = "workflow";
		String id = UUID.randomUUID().toString();
		while (this.findById(retVal + id) != null) {
			id = UUID.randomUUID().toString();
		}
		retVal += id;
		return retVal;
	}
}
