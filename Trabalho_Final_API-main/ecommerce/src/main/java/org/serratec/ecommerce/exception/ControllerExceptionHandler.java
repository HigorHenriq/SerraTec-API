package org.serratec.ecommerce.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return handleGeneralException(new GeneralException(new ErroResposta(
                HttpStatus.BAD_REQUEST,
                EXCLUSAO_EDICAO_FAIL,
                Arrays.asList(INTEGRIDADE_FAIL))));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException ex){
        return new ResponseEntity<>(ex.getErroResposta(), ex.getErroResposta().returnHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErroResposta erroResposta = new ErroResposta(status,
                CAMPOS_INVALIDOS,
                Collections.singletonList(ex.getMostSpecificCause().getMessage()));

        System.out.println(ex.getMostSpecificCause());

        if(ex.getCause() instanceof InvalidFormatException){
            erroResposta.setErrors(Collections.singletonList(
                    String.format("O dado informado '%s' não é um tipo válido de '%s'.",
                            ((InvalidFormatException)ex.getCause()).getValue().toString(),
                            ((MismatchedInputException)ex.getCause()).getTargetType().toString()
                    )));

        } else if(ex.getCause() instanceof JsonParseException){
            try {
                String field = ((JsonParseException)ex.getCause()).getProcessor().currentName();
                erroResposta.setErrors(Collections.singletonList(
                        String.format("Verifique novamente a propriedade: '%s'", field)));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(ex.getCause() instanceof UnrecognizedPropertyException){
            String erro = ((UnrecognizedPropertyException)ex.getCause()).getPropertyName();
            erroResposta.setErrors(Collections.singletonList(
                    String.format("Não foi possível localizar a propriedade: '%s'",
                            ((UnrecognizedPropertyException)ex.getCause()).getPropertyName()
                    )));
            System.out.println(erro);

        } else if(ex.getMostSpecificCause() instanceof GeneralException){
            return handleGeneralException((GeneralException) ex.getMostSpecificCause());
        }
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status,
                CAMPOS_INVALIDOS,
                errors);
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }
}
