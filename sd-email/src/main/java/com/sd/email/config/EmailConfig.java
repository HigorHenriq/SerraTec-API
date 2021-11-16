package com.sd.email.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.sd.email.domain.Email;
import com.sd.email.enums.StatusDeEnvio;
import com.sd.email.repository.EmailRepository;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailRepository emailRepository;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(Email emailModel) {
		emailModel.setDataDeEnvio(LocalDateTime.now());
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			
			msg.setFrom(emailModel.getEmailDe());
			msg.setTo(emailModel.getEmailPara());
			msg.setSubject(emailModel.getTitulo());
			msg.setText(emailModel.getCorpoMensagem());
			
			javaMailSender.send(msg);
			
			emailModel.setStatusDoEmail(StatusDeEnvio.ENVIADO);
		}
		catch (MailException e) {
			emailModel.setStatusDoEmail(StatusDeEnvio.ERRO);
		}
		
	}
}
