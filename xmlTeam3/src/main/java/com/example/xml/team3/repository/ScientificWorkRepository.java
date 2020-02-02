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

import com.example.xml.team3.model.scientificwork.ScientificWork;
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

	public static String scientificWorkCollectionId = "/db/team3/scientificWork";
	public static String scientificWorkSchemaPath = "src/main/resources/xsd/scientificWork.xsd";

	public String save(ScientificWork scientificWork) throws Exception {
		String Id = generateNewScientificWorkId();
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		ExistStore.store(scientificWorkCollectionId, Id, scientificWorkXML);
		return Id;
	}

	public String update(String id, ScientificWork scientificWork) throws Exception {
		ScientificWork oldScientificWork = this.findById(id);
		if (oldScientificWork == null) {
			throw new Exception("There is no scientific work with this id");
		}
		this.delete(id);
		String scientificWorkXML = marshallerUtil.marshallScientificWork(scientificWork);
		ExistStore.store(scientificWorkCollectionId, id, scientificWorkXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(scientificWorkCollectionId, id);
		if (mods == 0) {
			return false;
		}
		deleteMetadata(id);
		return true;
	}

	public ScientificWork findById(String id) throws Exception {
		String xQuery = "//scientificWork[@id=\"" + id + "\"" + "]";
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
		String xQuery = "//scientificWork[type=\"" + "ACCEPTED" + "\"" + "]";
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
}
