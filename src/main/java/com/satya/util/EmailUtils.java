package com.satya.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String sub,String body,String to,File f) throws Exception {
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true);
		msgHelper.setSubject(sub);
		msgHelper.setText(body,true);
		msgHelper.setTo(to);
		msgHelper.addAttachment("Plan-Details", f);
		mailSender.send(msg);
	}
}
