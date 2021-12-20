package org.serratec.ecommerce.produto;

import org.serratec.ecommerce.exception.GeneralException;
import org.serratec.ecommerce.produto.dto.ProdutoRespDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface ProdutoService {

    /** Pesquisa todos os produtos.
     * @return Uma lista de {@link Produto}
     */
    ResponseEntity<Set<ProdutoRespDTO>> pesquisar();

    /**
     * Pesquisa um produto pelo Nome
     * @param nome
     * @return {@link org.springframework.http} {@link Produto}
     */
    ResponseEntity<ProdutoRespDTO> pesquisar(String nome);

    /**
     * Insere um {@link Produto}
     * @param produto
     * @return {@link Produto}
     */
    ResponseEntity<ProdutoRespDTO> inserir(Produto produto);

    /**
     * Insere uma lista de {@link Produto}
     * @param produtos
     * @return Uma lista de {@link Produto}
     */
    List<Produto> inserir(List<Produto> produtos);

    /**
     * Atualiza um {@link Produto}
     * @param produto
     * @param id
     * @return
     */
    ResponseEntity<Produto> editar(Produto produto, Long id) throws GeneralException;

    /**
     * Remove um {@link Produto} por ID
     * @param id
     * @return
     */
    ResponseEntity<Void> remover(Long id);

}
