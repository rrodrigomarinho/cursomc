package com.rodrigomarinho.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.rodrigomarinho.cursomc.domain.Cliente;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class); 
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		LOG.info("Enviando e-mail...");
		mailSender.send(simpleMailMessage);
		LOG.info("E-mail enviado!");
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
	}

}
