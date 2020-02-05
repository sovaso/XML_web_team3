package com.example.xml.team3.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.util.Exist.ExistRetrieve;
import com.example.xml.team3.util.Exist.ExistStore;
import com.example.xml.team3.util.Exist.ExistUpdate;
import com.example.xml.team3.util.RDF.RDFStore;
import com.example.xml.team3.util.RDF.RDFUpdate;
import com.example.xml.team3.util.Template.XUpdateTemplate;
import com.example.xml.team3.util.jaxb.MarshallerUtil;
import com.example.xml.team3.util.jaxb.UnmarshallerUtil;

@Repository
public class ReviewRepository {

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	public static String reviewCollectionId = "/db/team3/review";
	public static String workflowCollectionId = "/db/team3/workflow";
	public static String reviewSchemaPath = "src/main/resources/xsd/review.xsd";

	public String save(Review review) throws Exception {
		String Id = generateNewReviewId();
		review.setId(Id);
		String reviewXML = marshallerUtil.marshallReview(review);
		ExistStore.store(reviewCollectionId, Id, reviewXML);
		return Id;
	}

	public String update(String id, Review review) throws Exception {
		Review oldReview = this.findById(id);
		if (oldReview == null) {
			throw new Exception("There is no cover letter with this id");
		}
		this.delete(id);
		String reviewXML = marshallerUtil.marshallReview(review);
		ExistStore.store(reviewCollectionId, id, reviewXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(reviewCollectionId, id);
		if (mods == 0) {
			return false;
		}
		deleteMetadata(id);
		return true;
	}

	public Review findById(String id) throws Exception {
		String xQuery = "//review[id=\"" + id + "\"" + "]";
		Review retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(reviewCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/review");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallReview(((XMLResource) res).getContent().toString());
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

	public String getWorkflowIdByScientificWorkId(String scientificWorkId) throws Exception {
		String xQuery = "//workflow[scientificWorkId=\"" + scientificWorkId + "\"" + "]";
		String retVal = "";
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(workflowCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/workflow");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallWorkflow(((XMLResource) res).getContent().toString()).getId();
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

	public List<Review> getAllByScientificWorkId(String scientificWorkId) throws Exception {
		String workflowId = this.getWorkflowIdByScientificWorkId(scientificWorkId);
		String xQuery = "//review[workflowId=\"" + workflowId + "\"" + "]";
		List<Review> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(reviewCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/review");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					Review r = new Review();
					r = unmarshallerUtil.unmarshallReview(((XMLResource) res).getContent().toString());
					retVal.add(r);
					// break;
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
		RDFStore.store(metadata, "/example/review/" + id);
	}

	public void deleteMetadata(String cvId) throws Exception {
		RDFUpdate.remove("/example/review/" + cvId);
	}

	public void updateMetadata(StringWriter metadata, String id) throws Exception {
		String url = "/example/review/" + id;
		deleteMetadata(id);
		RDFStore.store(metadata, url);
	}

	public String generateNewReviewId() throws Exception {
		String retVal = "review";
		String id = UUID.randomUUID().toString();
		while (this.findById(retVal + id) != null) {
			id = UUID.randomUUID().toString();
		}
		retVal += id;
		return retVal;
	}
}
