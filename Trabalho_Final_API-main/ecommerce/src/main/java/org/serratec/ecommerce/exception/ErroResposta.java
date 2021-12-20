package org.serratec.ecommerce.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

public class ErroResposta {

    @JsonProperty("Status")
    private HttpStatus status;

    @JsonProperty("Título")
    private String titulo;

    @JsonProperty("Data e Hora")
    private LocalDateTime dataHora;

    @JsonProperty("Erros")
    private List<String> errors;

    public ErroResposta(HttpStatus status, String titulo) {
        this.status = status;
        this.titulo = titulo;
        this.dataHora = LocalDateTime.now();
    }

    public ErroResposta(HttpStatus status, String titulo, List<String> errors) {
        this(status, titulo);
        this.errors = errors;
    }

    public String getStatus()
    {
        return new StringJoiner(" ")
                .add(String.valueOf(status.value()))
                .add(status.name()).toString();
    }

    public HttpStatus returnHttpStatus(){
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
