package org.serratec.ecommerce.utils;

import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class Existencia {
    public static void existeID(Long id, JpaRepository repository, HttpStatus httpStatus, String titulo, String erro){
        if(!repository.existsById(id)){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    titulo,
                    Arrays.asList(erro + id)));
        }
    }
}
