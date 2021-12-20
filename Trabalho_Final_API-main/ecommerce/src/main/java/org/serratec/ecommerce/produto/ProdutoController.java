package org.serratec.ecommerce.produto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.serratec.ecommerce.produto.dto.ProdutoRespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.*;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	/* Método para pesquisar todos os produtos - */

	@GetMapping("/pesquisar")
	@ApiOperation(value = PESQUISAR_TODOS_PRODUTOS, notes = NOTE_PESQUISAR_TODOS_PRODUTOS)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	public ResponseEntity<Set<ProdutoRespDTO>> pesquisar() {
		return this.produtoService.pesquisar();
	}

	/* Método para pesquisar um produto pelo nome- */

	@GetMapping("/pesquisarNome")
	@ApiOperation(value = PESQUISAR_PRODUTO_NOME, notes = NOTE_PESQUISAR_PRODUTO_NOME)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	public ResponseEntity<ProdutoRespDTO> pesquisar(@RequestParam(name = "Nome") String nome) {
		return this.produtoService.pesquisar(nome);
	}

	/* Método para inserir um produto */

	@PostMapping("/inserir")
	@ApiOperation(value = CRIAR_PRODUTO, notes = NOTE_CRIAR_PRODUTO)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoRespDTO> inserir(@Valid @RequestBody Produto produto) {
		return this.produtoService.inserir(produto);
	}

	/* Método para atualizar um produto por ID */

	@PutMapping("/editar")
	@ApiOperation(value = ATUALIZAR_PRODUTO, notes = NOTE_ATUALIZAR_PRODUTO)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<Produto> editar(@Valid @RequestBody Produto produto, @RequestParam(name = "ProdutoID") Long id) {
		return this.produtoService.editar(produto, id);
	}


	/*Método para deletar um produto por ID-*/

	@DeleteMapping("/remover")
	@ApiOperation(value = DELETAR_PRODUTO, notes = NOTE_DELETAR_PRODUTO)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	
	public ResponseEntity<Void> remover(@RequestParam(name = "ProdutoID") Long id) {
		return this.produtoService.remover(id);
	}
}
