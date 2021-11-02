package org.serratec.java2backend.exercicio02.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

	/*
	 * Esse metodo está reescrevendo o handleMethodArgumentNotValid
	 * Para o cliente ter um erro mais amigavel e saber o que foi
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	HttpHeaders headers, HttpStatus status, WebRequest request){
		
		List<String> msgErrors = new ArrayList<String>();
		
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			msgErrors.add(error.getField() + ":" + error.getDefaultMessage());
			
		}
		
		ErroResposta erroResposta = new ErroResposta(status.value(), "Existem Campos Inválidos. Confira o preenchimento.",
				LocalDateTime.now(), msgErrors);
		
		//METODO ABAIXO DO PROFESSOR, O METODO NÃO NECESSITA DO SUPER.
		//return ResponseEntity.badRequest().body(new ErroResposta(status.ordinal(), "Existem campos inválidos. Confira o preenchimento", LocalDateTime.now()));
		
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
}
