package org.serratec.ecommerce.categoria;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.serratec.ecommerce.utils.Mensagens.ApiResponses.*;
import static org.serratec.ecommerce.utils.Mensagens.SwaggerMessages.*;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	/* Método para pesquisar todas as categorias */

	@GetMapping("/pesquisar")
	@ApiOperation(value = PESQUISAR_TODAS_CATEGORIAS, notes = NOTE_PESQUISAR_TODAS_CATEGORIAS)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<List<Categoria>> pesquisar() {
		return this.categoriaService.pesquisar();
	}
	
	/* Método para pesquisar categorias por nome */

	@GetMapping("/pesquisarNome")
	@ApiOperation(value = PESQUISAR_CATEGORIA_NOME, notes = NOTE_PESQUISAR_CATEGORIA_NOME)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<Categoria> pesquisar(@RequestParam(name = "Nome") String nome) {
		return this.categoriaService.pesquisar(nome);
	}
	/* Método para pesquisar categoria por ID */
	
	@GetMapping("/pesquisarID")
	@ApiOperation(value = PESQUISAR_CATEGORIA_ID, notes = NOTE_PESQUISAR_CATEGORIA_ID)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<Categoria> pesquisar(@RequestParam(name = "CategoriaID") Long id) {
		return this.categoriaService.pesquisar(id);
		
	}
	
	/* Método para inserir uma categoria */

	@PostMapping("/inserir")
	@ApiOperation(value = INSERIR_CATEGORIA, notes = NOTE_INSERIR_CATEGORIA)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return this.categoriaService.inserir(categoria);
	}
	
	/* Método para editar categoria por ID */

	@PutMapping("/editar")
	@ApiOperation(value = EDITAR_CATEGORIA, notes = NOTE_EDITAR_CATEGORIA)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<Categoria> editar(@Valid @RequestBody Categoria categoria, @RequestParam(name = "CategoriaID") Long id) {
		return this.categoriaService.editar(categoria, id);
	}
	
	/* Método para deletar categoria por ID */

	@DeleteMapping("/remover")
	@ApiOperation(value = REMOVER_CATEGORIA, notes = NOTE_REMOVER_CATEGORIA)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = RESPONSE_200),
			@ApiResponse(code = 401, message = ERROR_401),
			@ApiResponse(code = 403, message = ERROR_403),
			@ApiResponse(code = 404, message = ERROR_404),
			@ApiResponse(code = 505, message = ERROR_405) })
	
	public ResponseEntity<Categoria> remover(@RequestParam(name = "CategoriaID") Long id) {
		return this.categoriaService.remover(id);
	}
}
