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

import com.example.xml.team3.model.grades.Grades;
import com.example.xml.team3.util.Exist.ExistRetrieve;
import com.example.xml.team3.util.Exist.ExistStore;
import com.example.xml.team3.util.Exist.ExistUpdate;
import com.example.xml.team3.util.RDF.RDFStore;
import com.example.xml.team3.util.RDF.RDFUpdate;
import com.example.xml.team3.util.Template.XUpdateTemplate;
import com.example.xml.team3.util.jaxb.MarshallerUtil;
import com.example.xml.team3.util.jaxb.UnmarshallerUtil;

@Repository
public class GradesRepository {

	@Autowired
	ExistRetrieve existRetrieve;

	@Autowired
	UnmarshallerUtil unmarshallerUtil;

	@Autowired
	MarshallerUtil marshallerUtil;

	public static String gradesCollectionId = "/db/team3/grades";
	public static String gradesSchemaPath = "src/main/resources/xsd/grades.xsd";

	public String save(Grades grades) throws Exception {
		String Id = generateNewGradesId();
		String gradesXML = marshallerUtil.marshallGrades(grades);
		ExistStore.store(gradesCollectionId, Id, gradesXML);
		return Id;
	}

	public String update(String id, Grades grades) throws Exception {
		Grades oldGrades = this.findById(id);
		if (oldGrades == null) {
			throw new Exception("There is no cover letter with this id");
		}
		this.delete(id);
		String gradesXML = marshallerUtil.marshallGrades(grades);
		ExistStore.store(gradesCollectionId, id, gradesXML);
		return id;
	}

	public boolean delete(String id) throws Exception {
		long mods = ExistUpdate.delete(gradesCollectionId, id);
		if (mods == 0) {
			return false;
		}
		deleteMetadata(id);
		return true;
	}

	public Grades findById(String id) throws Exception {
		String xQuery = "//grades[@id=\"" + id + "\"" + "]";
		Grades retVal = null;
		try {
			ResourceSet result = ExistRetrieve.executeXPathExpression(gradesCollectionId, xQuery,
					XUpdateTemplate.TARGET_NAMESPACE + "/grades");
			ResourceIterator it = result.getIterator();
			Resource res = null;

			while (it.hasMoreResources()) {
				try {
					res = it.nextResource();
					retVal = unmarshallerUtil.unmarshallGrades(((XMLResource) res).getContent().toString());
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
		RDFStore.store(metadata, "/example/grades/" + id);
	}

	public void deleteMetadata(String cvId) throws Exception {
		RDFUpdate.remove("/example/grades/" + cvId);
	}

	public void updateMetadata(StringWriter metadata, String id) throws Exception {
		String url = "/example/grades/" + id;
		deleteMetadata(id);
		RDFStore.store(metadata, url);
	}

	public String generateNewGradesId() throws Exception {
		String retVal = "grades";
		String id = UUID.randomUUID().toString();
		while (this.findById(retVal + id) != null) {
			id = UUID.randomUUID().toString();
		}
		retVal += id;
		return retVal;
	}
}
