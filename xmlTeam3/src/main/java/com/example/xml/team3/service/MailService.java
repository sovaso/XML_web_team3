package com.example.xml.team3.service;

public interface MailService {

	public void sendMailNotification(String xsdPath, String xslPath, String xmlValue, String senderMail,
			String receiverMail, String subject, String text);
}
