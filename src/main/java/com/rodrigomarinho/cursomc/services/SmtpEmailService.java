package com.rodrigomarinho.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class); 
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		LOG.info("Enviando e-mail...");
		mailSender.send(simpleMailMessage);
		LOG.info("E-mail enviado!");
	}


	@Override
	public void sendHtmlEmail(MimeMessage mimeMessage) {
		LOG.info("Enviando e-mail...");
		javaMailSender.send(mimeMessage);
		LOG.info("E-mail enviado!");
	}

}
