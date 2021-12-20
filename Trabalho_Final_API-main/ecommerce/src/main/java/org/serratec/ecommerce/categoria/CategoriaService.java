package org.serratec.ecommerce.categoria;

import org.serratec.ecommerce.exception.GeneralException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/** Interface de serviço da {@link Categoria}
 * @author Nathan Guimarães
 */
public interface CategoriaService {


    /** Pesquisa todos as {@link Categoria}.
     * @return Uma lista de {@link Categoria}
     */
    ResponseEntity<List<Categoria>> pesquisar();

    /**
     * Pesquisa uma {@link Categoria} pelo nome
     * @param nome
     * @return {@link Categoria}
     */
    ResponseEntity<Categoria> pesquisar(String nome);

    /**
     * Pesquisa uma {@link Categoria} pelo id
     * @param id
     * @return {@link Categoria}
     */
    ResponseEntity<Categoria> pesquisar(Long id);

    /**
     * Insere um {@link Categoria}
     * @param categoria
     * @return {@link Categoria}
     */
    Categoria inserir(Categoria categoria);

    /**
     * Insere uma lista de {@link Categoria}
     * @param categorias
     * @return Uma lista de {@link Categoria}
     */
    List<Categoria> inserir(List<Categoria> categorias);

    /**
     * Atualiza um {@link Categoria}
     * @param categoria
     * @param id
     * @return
     */
    ResponseEntity<Categoria> editar(Categoria categoria, Long id) throws GeneralException;

    /**
     * Remove um {@link Categoria} por ID
     * @param id
     * @return
     */
    ResponseEntity<Categoria> remover(Long id);

}
