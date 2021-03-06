package com.example.xml.team3.repository;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.example.xml.team3.dto.SearchDTO;
import com.example.xml.team3.model.scientificwork.Paragraph;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.scientificwork.StatusType;
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
public class ScientificWorkRepository {

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	@Autowired
	WorkflowRepository workflowRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	XUpdateTemplate xUpdateTemplate;

	public static String scientificWorkCollectionId = "/db/team3/scientificWork";
	public static String workflowCollectionId = "/db/team3/workflow";
	public static String scientificWorkSchemaPath = "src/main/resources/xsd/scientificWork.xsd";
	public static String workflowSchemaPath = "src/main/resources/xsd/workflow.xsd";

	public String save(ScientificWork scientificWork) throws Exception {
		String Id = generateNewScientificWorkId();
		for (Paragraph p : scientificWork.getParagraph()) {
			p.setId(Id + "/paragraph" + UUID.randomUUID().toString());
		}
		scientificWork.setId(Id);
		// napraviti workflow novi jer pocinje proces objave
		Workflow w = new Workflow();
		w.setAuthorUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		w.setEditorUsername("");
		w.setReviewerUsername("");
		w.setScientificWorkId(Id);
		workflowRepository.save(w);
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		ExistStore.store(scientificWorkCollectionId, Id, scientificWorkXML);
		return Id;
	}

	public String update(String id, ScientificWork scientificWork) throws Exception {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("Id in scientific work repository: " + id);
		System.out.println("Title in scentific work: " + scientificWork.getTitle());
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		  ScientificWork oldScientificWork = this.findById(id); if (oldScientificWork
		  == null) { System.out.println("Scientific work je null"); throw new
		  Exception("There is no scientific work with this id"); }
		 
		System.out.println("Pre delete in repository");
		this.delete(id);
		System.out.println("Posle delete in repository");
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		ExistStore.store(scientificWorkCollectionId,id,scientificWorkXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(scientificWorkCollectionId, id);
		if (mods == 0) {
			return false;
		}
		// deleteMetadata(id);
		return true;
	}

	public ScientificWork findById(String id) throws Exception {
		String xQuery = "//scientificWork[id=\"" + id + "\"" + "]";
		ScientificWork retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
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

	public List<ScientificWork> findAllPublished() {
		String xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\"" + "]";
		List<ScientificWork> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);

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

	public List<ScientificWork> getAllForEditor() {
		String xQuery = "//scientificWork[not(status=\"" + "ACCEPTED"
				+ "\") and not(status=\"WITHDRAWN\") and not(status=\"REJECTED\")" + "]";
		List<ScientificWork> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);

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

	public List<ScientificWork> findAllSubmitted() {
		String xQuery = "//scientificWork[status=\"" + "SUBMITTED" + "\"" + "]";
		List<ScientificWork> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);

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

	public List<ScientificWork> findAllForRevision(String username) {
		String xQuery = "//scientificWork[status=\"" + "REVISING" + "\"" + " and ./authors/author[@username=\""
				+ username + "\"]]";
		List<ScientificWork> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);

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

	public List<ScientificWork> findAllForConcreteUser(String username) {
		List<ScientificWork> retVal = new ArrayList<>();

		// uzmi sve prihvacene
		// String xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\"" + "]";
		String xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\"" + " and ./authors/author[@username=\""
				+ username + "\"]]";
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				System.out.println("uslo u while 1");
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);
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
		// uzmi sve koji nisu prihvaceni a pripadaju useru
		String xQuery2 = "//scientificWork[not(status=\"" + "ACCEPTED" + "\")" + " and ./authors/author[@username=\""
				+ username + "\"]]";
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery2,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				System.out.println("Uslo u while2");
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					System.out.println("aaaa");
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					System.out.println("bbb");
					retVal.add(sw);
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
		System.out.println("Retval size: " + retVal.size());
		return retVal;
	}

	public List<ScientificWork> findAllForReviewing(String reviewerUsername) {
		List<ScientificWork> retVal = new ArrayList<>();
		// uzmi sve prihvacene
		// String xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\"" + "]";
		String xQuery = "//workflow[reviewerUsername=\"" + reviewerUsername + "\"]";
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(workflowCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/workflow");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				System.out.println("uslo u while 1");
				try {
					res = it.nextResource();
					Workflow w = new Workflow();
					w = unmarshallerUtil.unmarshallWorkflow(((XMLResource) res).getContent().toString());
					ScientificWork sw = this.findById(w.getScientificWorkId());
					if (sw!=null) {
						if (sw.getStatus() == StatusType.REVIEWING) {
							retVal.add(sw);
						}
					}
					
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
		System.out.println("Retval size: " + retVal.size());
		return retVal;
	}

	public void saveMetadata(StringWriter metadata, String id) throws Exception {
		RDFStore.store(metadata, "/example/scientificWork/" + id);
	}

	public void deleteMetadata(String cvId) throws Exception {
		RDFUpdate.remove("/example/scientificWork/" + cvId);
	}

	public void updateMetadata(StringWriter metadata, String id) throws Exception {
		String url = "/example/scientificWork/" + id;
		deleteMetadata(id);
		RDFStore.store(metadata, url);
	}

	public String generateNewScientificWorkId() throws Exception {
		String retVal = "scientificWork";
		String id = UUID.randomUUID().toString();
		while (this.findById(retVal + id) != null) {
			id = UUID.randomUUID().toString();
		}
		retVal += id;
		return retVal;
	}

	public List<ScientificWork> searchScientificWork(SearchDTO searchDTO, String username) {
		String xQuery = "";
		if (username.equalsIgnoreCase("")) {
			xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\""
					+ " and (./abstract[descendant::*[contains(text(),\"" + searchDTO.getText()
					+ "\")]] or ./paragraph[text[contains(text(),\"" + searchDTO.getText() + "\")]] "
					+ ") and ./title[contains(text(),\"" + searchDTO.getTitle() + "\")] and"
					+ " concat(./authors/author/name,\" \",./authors/author/surname)[contains(., \""
					+ searchDTO.getAuthor() + "\")]]";

			/*
			 * xQuery = "//scientificWork[status=\"" + "ACCEPTED" + "\"" +
			 * " and (./abstract[descendant::*[contains(.,\"" + searchDTO.getText() +
			 * "\")]] or ./paragraph[text[contains(.,\"" + searchDTO.getText() + "\")]] " +
			 * ") and ./title[contains(.,\"" + searchDTO.getTitle() + "\")] and" +
			 * " contains(concat(./authors/author/name,\" \",./authors/author/surname),\"" +
			 * searchDTO.getAuthor() + "\")]";
			 */
		} else {

			xQuery = "//scientificWork[./authors/author[@username=\"" + username + "\"]"
					+ " and (./abstract[descendant::*[contains(text(),\"" + searchDTO.getText()
					+ "\")]] or ./paragraph[text[contains(text(),\"" + searchDTO.getText() + "\")]] "
					+ ") and ./title[contains(text(),\"" + searchDTO.getTitle() + "\")] and"
					+ " concat(./authors/author/name,\" \",./authors/author/surname)[contains(., \""
					+ searchDTO.getAuthor() + "\")]]";

			/*
			 * 
			 * xQuery = "//scientificWork[(status=\"" + "ACCEPTED" + "\" or (not(status=\""
			 * + "ACCEPTED" + "\")" + " and ./authors/author[@username=\"" + username +
			 * "\"]))" + " and ./abstract[descendant::*[contains(.,\"" + searchDTO.getText()
			 * + "\")]] and ./paragraph[text[contains(.,\"" + searchDTO.getText() + "\")]] "
			 * + " and ./title[contains(.,\"" + searchDTO.getTitle() + "\"] and " +
			 * "contains(concat(./authors/author/name,\" \",./authors/author/surname),\"" +
			 * searchDTO.getAuthor() + "\") and ]";
			 * 
			 */

		}

		List<ScientificWork> retVal = new ArrayList<>();
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(scientificWorkCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/scientificWork");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					ScientificWork sw = new ScientificWork();
					sw = unmarshallerUtil.unmarshallScientificWork(((XMLResource) res).getContent().toString());
					retVal.add(sw);

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
}
