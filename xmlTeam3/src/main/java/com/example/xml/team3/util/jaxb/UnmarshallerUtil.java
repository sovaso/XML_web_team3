package com.example.xml.team3.util.jaxb;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.example.xml.team3.model.coverletter.CoverLetter;
import com.example.xml.team3.model.grades.Grades;
import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.workflow.Workflow;

@Component
public class UnmarshallerUtil {

	public UserPub unmarshallUser(String user) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserPub.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(user);
			UserPub retVal = (UserPub) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ScientificWork unmarshallScientificWork(String scientificWork) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ScientificWork.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(scientificWork);
			ScientificWork retVal = (ScientificWork) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoverLetter unmarshallCoverLetter(String coverLetter) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CoverLetter.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(coverLetter);
			CoverLetter retVal = (CoverLetter) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Grades unmarshallGrades(String grades) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Grades.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(grades);
			Grades retVal = (Grades) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Review unmarshallReview(String review) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(review);
			Review retVal = (Review) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Workflow unmarshallWorkflow(String workflow) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Workflow.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(workflow);
			Workflow retVal = (Workflow) unmarshaller.unmarshal(reader);
			return retVal;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
