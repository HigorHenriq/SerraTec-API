package org.serratec.ecommerce.exception;


public class GeneralException extends RuntimeException {

    private final ErroResposta erroResposta;

    public GeneralException(ErroResposta erroResposta) {
        super(erroResposta.getTitulo());
        this.erroResposta = erroResposta;
    }

    public ErroResposta getErroResposta() {
        return erroResposta;
    }
}
