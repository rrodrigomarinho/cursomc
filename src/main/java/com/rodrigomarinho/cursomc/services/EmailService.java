package com.rodrigomarinho.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.rodrigomarinho.cursomc.domain.Cliente;
import com.rodrigomarinho.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage simpleMailMessage);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage mimeMessage);
}
