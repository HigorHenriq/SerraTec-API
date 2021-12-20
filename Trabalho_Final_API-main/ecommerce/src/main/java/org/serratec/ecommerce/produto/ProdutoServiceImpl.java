package org.serratec.ecommerce.produto;

import org.modelmapper.ModelMapper;
import org.serratec.ecommerce.categoria.Categoria;
import org.serratec.ecommerce.categoria.CategoriaRepository;
import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.serratec.ecommerce.produto.dto.ProdutoRespDTO;
import org.serratec.ecommerce.utils.Existencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Set<ProdutoRespDTO>> pesquisar() {
        List<Produto> produtos = this.produtoRepository.findAll();
        return Optional.of(toDTO(produtos))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProdutoRespDTO> pesquisar(String nome) {
        try {
            Optional<Produto> produto = this.produtoRepository.findByNomeIgnoreCase(nome);
            return Optional.of(modelMapper.map(produto.get(), ProdutoRespDTO.class))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        }catch (NoSuchElementException | NullPointerException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    PESQUISA_FALHOU,
                    Arrays.asList(PESQUISA_PRODUTO_FALHOU + nome)));
        }
    }

    @Override
    public ResponseEntity<ProdutoRespDTO> inserir(Produto produto) throws GeneralException {

        try {
            // Verifica Categoria Já Existente
            if(this.categoriaRepository.findByNomeIgnoreCase(produto.getCategoria().getNome()).isPresent()){
                Optional<Categoria> categoria = this.categoriaRepository.findByNomeIgnoreCase(produto.getCategoria().getNome());
                produto.setCategoria(categoria.get());
            }

            this.produtoRepository.save(produto);
            return ResponseEntity.ok(new ProdutoRespDTO(produto));
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(
                    HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_PEDIDO_FALHOU + produto.getNome())));
        }
    }

//    @Override
//    public ResponseEntity<Produto> editarCategoria(Long idCategoria, Long idProduto) {
//        if (!this.produtoRepository.existsById(idProduto)) {
//            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
//                    INSERCAO_CATEGORIA_FALHOU,
//                    Arrays.asList(PESQUISA_PRODUTO_FALHOU + idProduto)));
//        }
//
//        if (!this.categoriaRepository.existsById(idCategoria)) {
//            throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND,
//                    INSERCAO_CATEGORIA_FALHOU,
//                    Arrays.asList(PESQUISA_CATEGORIA_FALHOU + idCategoria)));
//        }
//
//        Produto produto = this.produtoRepository.getById(idProduto);
//
//        try {
//            produto.setCategoria(this.categoriaRepository.getById(idCategoria));
//            produto = this.produtoRepository.save(produto);
//            return ResponseEntity.ok(produto);
//        }
//        catch (IllegalArgumentException ex){
//            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
//                    EDICAO_FALHOU,
//                    Arrays.asList(EDICAO_CATEGORIA_FALHOU + idCategoria)));
//        }
//    }

    @Override
    public ResponseEntity<Void> remover(Long id) throws GeneralException{
        Existencia.existeID(id, this.produtoRepository, HttpStatus.NOT_FOUND, EXCLUSAO_FALHOU, PESQUISA_PRODUTO_FALHOU);

        try {
            this.produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EXCLUSAO_FALHOU,
                    Arrays.asList(EXCLUSAO_PRODUTO_FALHOU + id)));
        }
    }

    @Override
    public List<Produto> inserir(List<Produto> produtos) throws GeneralException{
        try {
            return this.produtoRepository.saveAll(produtos);
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    INSERCAO_FALHOU,
                    Arrays.asList(INSERCAO_PEDIDO_FALHOU)));
        }
    }

    @Override
    public ResponseEntity<Produto> editar(Produto produto, Long id) throws GeneralException {
        Existencia.existeID(id, this.produtoRepository, HttpStatus.NOT_FOUND, EDICAO_FALHOU, PESQUISA_PRODUTO_FALHOU);

        try {
            produto.setId(id);
            produto = this.produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        }
        catch (IllegalArgumentException ex){
            throw new GeneralException(new ErroResposta(HttpStatus.BAD_REQUEST,
                    EDICAO_FALHOU,
                    Arrays.asList(EDICAO_PRODUTO_FALHOU + produto.getNome())));
        }
    }

    private Set<ProdutoRespDTO> toDTO(List<Produto> produtos){
        return produtos.stream().map(produto -> modelMapper.map(produto, ProdutoRespDTO.class)).collect(Collectors.toSet());
    }
}
