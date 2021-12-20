package org.serratec.ecommerce.pedido;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.StringJoiner;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.CAMPOS_INVALIDOS;
import static org.serratec.ecommerce.utils.Mensagens.Exceptions.PREENCHIMENTO_INCORRETO;

public enum StatusPedido {

    PROCESSANDO(1, "Processando"),
    FINALIZADO(2, "Finalizado"),
    CANCELADO(3, "Cancelado");

    private final Integer codigo;
    private final String tipo;

    StatusPedido(Integer codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    @JsonCreator
    public static StatusPedido verifica(Integer value) throws GeneralException {
        String errorMsg = PREENCHIMENTO_INCORRETO;
        StringJoiner joiner = new StringJoiner(" - ");
        for (StatusPedido c : values()) {
            joiner.add(c.getCodigo() + ") " + c.getTipo());// errorMsg += c.getCodigo() + ") " + c.getTipo() + " ";
            if (value.equals(c.getCodigo())) {
                return c;
            }
        }
        errorMsg += joiner.toString();
        throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                CAMPOS_INVALIDOS,
                Arrays.asList(errorMsg)));
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }
}
