package com.example.xml.team3.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.xml.team3.service.MailService;
import com.example.xml.team3.util.XsltUtil;

@Service
public class MailServiceImpl implements MailService {

	private final JavaMailSender mailSender;

	@Autowired
	public MailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendMailNotification(String xsdPath, String xslPath, String xmlValue, String senderMail,
			String receiverMail, String subject, String text) {
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
			message.setTo(receiverMail);
			message.setFrom(senderMail);
			message.setSubject(subject);
			message.setText(text, true);
			message.addAttachment("transform.html", new ByteArrayResource(xmlValue.getBytes(StandardCharsets.UTF_8)));
			message.addAttachment("transform.pdf", new ByteArrayResource(
					IOUtils.toByteArray(XsltUtil.toPdf(xmlValue, xslPath, xsdPath).getInputStream())));
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
