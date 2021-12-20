package org.serratec.ecommerce.categoria;

import org.serratec.ecommerce.exception.ErroResposta;
import org.serratec.ecommerce.exception.GeneralException;
import org.serratec.ecommerce.pedido.PedidoController;
import org.serratec.ecommerce.utils.Existencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.serratec.ecommerce.utils.Mensagens.Exceptions.*;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private PedidoController pedidoController;

	@Override
	public ResponseEntity<List<Categoria>> pesquisar() {
		Optional<List<Categoria>> categorias = Optional.of(this.categoriaRepository.findAll());
		return categorias.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Categoria> pesquisar(String nome) {
		Optional<Categoria> categoria = this.categoriaRepository.findByNomeIgnoreCase(nome);
		return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@Override
	public ResponseEntity<Categoria> pesquisar(Long id) {
        Optional<Categoria> categoria= this.categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public Categoria inserir(Categoria categoria) {
		try {
			return this.categoriaRepository.save(categoria);
		}catch (IllegalArgumentException ex){
			throw new GeneralException(
					new ErroResposta(HttpStatus.BAD_REQUEST,
							EXCLUSAO_FALHOU,
							Arrays.asList(EXCLUSAO_CATEGORIA_FALHOU + categoria.getNome())));
		}
	}

	@Override
	public ResponseEntity<Categoria> remover(Long id) throws GeneralException {
		Existencia.existeID(id, this.categoriaRepository, HttpStatus.NOT_FOUND, EXCLUSAO_FALHOU, PESQUISA_CATEGORIA_FALHOU);

		this.categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public List<Categoria> inserir(List<Categoria> categorias) {
		return this.categoriaRepository.saveAll(categorias);
	}


	@Override
	public ResponseEntity<Categoria> editar(Categoria categoria, Long id) throws GeneralException {
		if (!this.categoriaRepository.existsById(id)) {
			throw new GeneralException(new ErroResposta(HttpStatus.NOT_FOUND, EDICAO_FALHOU,
					Arrays.asList(PESQUISA_CATEGORIA_FALHOU + id)));
		}

		categoria.setId(id);
		categoria = this.categoriaRepository.save(categoria);
		return ResponseEntity.ok(categoria);
	}
}