package com.rodrigomarinho.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.Pedido;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class); 
	
	@Override
	public void sendEmail(SimpleMailMessage simpleMailMessage) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(simpleMailMessage.toString());
		LOG.info("E-mail enviado!");
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
	}

	@Override
	public void sendHtmlEmail(MimeMessage mimeMessage) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(mimeMessage.toString());
		LOG.info("E-mail enviado!");
	}
}
