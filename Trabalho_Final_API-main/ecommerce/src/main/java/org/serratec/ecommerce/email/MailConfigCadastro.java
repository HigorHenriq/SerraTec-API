package org.serratec.ecommerce.email;

import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;


//CLASSE PARA ENVIAR O EMAIL PARA QUANDO UM CLIENTE SE REGISTRAR
@Configuration
public class MailConfigCadastro {

	private final String EMAIL_ADDRESS = "serviceemail458@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;

    public StatusDeEnvio enviarEmail(String para, String assunto, String corpo) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(EMAIL_ADDRESS);
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(corpo);

        try{
            javaMailSender.send(message);
            return StatusDeEnvio.ENVIADO;
        }catch (MailException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(ERRO_EMAIL + ex.getMessage())));
        }
    }
}
