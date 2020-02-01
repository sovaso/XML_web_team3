package com.example.xml.team3.util.jaxb;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.stereotype.Component;

import com.example.xml.team3.model.coverletter.CoverLetter;
import com.example.xml.team3.model.grades.Grades;
import com.example.xml.team3.model.review.Review;
import com.example.xml.team3.model.scientificwork.ScientificWork;
import com.example.xml.team3.model.user.UserPub;
import com.example.xml.team3.model.workflow.Workflow;

@Component
public class MarshallerUtil {

	public String marshallUser(UserPub person) {
		try {
			JAXBContext context = JAXBContext.newInstance(UserPub.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(person, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String marshallScientificWork(ScientificWork scientificWork) {
		try {
			JAXBContext context = JAXBContext.newInstance(ScientificWork.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(scientificWork, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String marshallCoverLetter(CoverLetter coverLetter) {
		try {

			JAXBContext context = JAXBContext.newInstance(CoverLetter.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(coverLetter, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String marshallGrades(Grades grades) {
		try {
			JAXBContext context = JAXBContext.newInstance(Grades.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(grades, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String marshallReview(Review review) {
		try {
			JAXBContext context = JAXBContext.newInstance(Review.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(review, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String marshallWorkflow(Workflow workflow) {
		try {
			JAXBContext context = JAXBContext.newInstance(Workflow.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(workflow, stream);
			return new String(stream.toByteArray());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "";
	}

}
