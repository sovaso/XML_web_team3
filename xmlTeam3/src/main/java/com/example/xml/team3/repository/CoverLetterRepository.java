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

import com.example.xml.team3.model.coverletter.CoverLetter;
import com.example.xml.team3.util.Exist.ExistRetrieve;
import com.example.xml.team3.util.Exist.ExistStore;
import com.example.xml.team3.util.Exist.ExistUpdate;
import com.example.xml.team3.util.RDF.RDFStore;
import com.example.xml.team3.util.RDF.RDFUpdate;
import com.example.xml.team3.util.Template.XUpdateTemplate;
import com.example.xml.team3.util.jaxb.MarshallerUtil;
import com.example.xml.team3.util.jaxb.UnmarshallerUtil;

@Repository
public class CoverLetterRepository {

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	public static String coverLetterCollectionId = "/db/team3/coverLetter";
	public static String coverLetterSchemaPath = "src/main/resources/xsd/coverLetter.xsd";

	public String save(CoverLetter coverLetter) throws Exception {
		String Id = generateNewCoverLetterId();
		String coverLetterXML = marshallerUtil.marshallCoverLetter(coverLetter);
		ExistStore.store(coverLetterCollectionId, Id, coverLetterXML);
		return Id;
	}

	public String update(String id, CoverLetter coverLetter) throws Exception {
		CoverLetter oldCoverLetter = this.findById(id);
		if (oldCoverLetter == null) {
			throw new Exception("There is no cover letter with this id");
		}
		this.delete(id);
		String coverLetterXML = marshallerUtil.marshallCoverLetter(coverLetter);
		ExistStore.store(coverLetterCollectionId, id, coverLetterXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(coverLetterCollectionId, id);
		if (mods == 0) {
			return false;
		}
		deleteMetadata(id);
		return true;
	}

	public CoverLetter findById(String id) throws Exception {
		String xQuery = "//coverLetter[@id=\"" + id + "\"" + "]";
		CoverLetter retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(coverLetterCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/coverLetter");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallCoverLetter(((XMLResource) res).getContent().toString());
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

	public CoverLetter findByScientificWorkId(String id) throws Exception {
		String xQuery = "//coverLetter[scientificWorkId=\"" + id + "\"" + "]";
		CoverLetter retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(coverLetterCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/coverLetter");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallCoverLetter(((XMLResource) res).getContent().toString());
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
		RDFStore.store(metadata, "/example/coverLetter/" + id);
	}

	public void deleteMetadata(String cvId) throws Exception {
		RDFUpdate.remove("/example/coverLetter/" + cvId);
	}

	public void updateMetadata(StringWriter metadata, String id) throws Exception {
		String url = "/example/coverLetter/" + id;
		deleteMetadata(id);
		RDFStore.store(metadata, url);
	}

	public String generateNewCoverLetterId() throws Exception {
		String retVal = "coverLetter";
		String id = UUID.randomUUID().toString();
		while (this.findById(retVal + id) != null) {
			id = UUID.randomUUID().toString();
		}
		retVal += id;
		return retVal;
	}
}
