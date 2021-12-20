package org.serratec.ecommerce.endereco;

import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.http.ResponseEntity;

public interface EnderecoService {
    Endereco inserir(Endereco endereco) throws GeneralException;
    ResponseEntity<Endereco> pesquisar(String cep);
    ResponseEntity<Endereco> editar(Long id, Endereco endereco);
    ResponseEntity<Void> deletar(Long id);
}
