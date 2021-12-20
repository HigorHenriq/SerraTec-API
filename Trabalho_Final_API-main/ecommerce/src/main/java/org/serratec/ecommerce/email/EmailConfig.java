package org.serratec.ecommerce.email;

import org.serratec.ecommerce.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;


@Configuration
public class EmailConfig {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(EmailModel emailModel, Cliente cliente) {
		emailModel.setDataDeEnvio(LocalDateTime.now());
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setFrom("serviceemail458@gmail.com");
			msg.setTo(cliente.getEmail());
			msg.setSubject("Detalhes da compra");
			msg.setText("Dados ferentes ao seu pedido:");
			msg.setText(cliente.toString());
			
			javaMailSender.send(msg);
			
			emailModel.setStatusDoEmail(StatusDeEnvio.ENVIADO);
		}
		catch (MailException e) {
			emailModel.setStatusDoEmail(StatusDeEnvio.ERRO);
		}
		
	}
}
