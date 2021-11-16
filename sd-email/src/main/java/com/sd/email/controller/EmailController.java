package com.sd.email.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sd.email.config.EmailConfig;
import com.sd.email.domain.Email;
import com.sd.email.dto.EmailDTO;

@RestController
public class EmailController {

	@Autowired
	private EmailConfig emailService;

	@PostMapping(value = "/api/enviarEmail")
	public ResponseEntity<Email> enviar(@Valid @RequestBody EmailDTO emailDto){
		Email emailModel = new Email();
		//METODO DE CONVERSÃO DE EmailDTO PARA EmailModel
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		return new ResponseEntity<Email>(emailModel, HttpStatus.CREATED);
	}
}
