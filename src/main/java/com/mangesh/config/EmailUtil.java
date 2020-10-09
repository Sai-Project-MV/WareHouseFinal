package com.mangesh.config;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailUtil {

	private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);
	@Autowired
	private JavaMailSender sender;

	public boolean sendMail(String to, String subject, String text, String[] cc, String[] bcc, MultipartFile file) {
		log.info("sendMail() Method Execution started....!!");
		boolean flag = true;
		try {
			// 1 create empty message (MIME)
			MimeMessage message = sender.createMimeMessage();

			// 2 use helper class object
			MimeMessageHelper helper = new MimeMessageHelper(message, file != null);
//set message details
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			if (cc != null) {
				helper.setCc(cc);
			}
			if (bcc != null) {
				helper.setCc(bcc);
			}
			if (file != null) {
				helper.addAttachment(file.getOriginalFilename(), file);
			}
			// send message
			sender.send(message);
		} catch (Exception e) {
			log.error("SendMail() Method Execution error happend....!!");
			e.printStackTrace();
			flag = false;
		}
		log.info("sendMail() Method Execution Ended....!!");
		return flag;
	}

	// overloaded Method
	public boolean sendMail(String to, String subject, String text) {
		return sendMail(to, subject, text, null, null, null);
	}

}
