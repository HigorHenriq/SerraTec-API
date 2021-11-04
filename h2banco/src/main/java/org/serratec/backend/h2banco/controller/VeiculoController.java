package org.serratec.backend.h2banco.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.h2banco.domain.Veiculo;
import org.serratec.backend.h2banco.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/h2veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	@ApiOperation(value = "Retorna todos os veículos cadastrados", notes = "listagem de veículos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os veículos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<List<Veiculo>> Lista(){
		List<Veiculo> listaVeiculos = veiculoRepository.findAll();
		return ResponseEntity.ok(listaVeiculos);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "Retorna um cliente", notes = "Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200 , message = "Retorna um cliente"),
			@ApiResponse(code = 401 , message = "Erro de autenticação"),
			@ApiResponse(code = 403 , message = "Você não tem permissão para acessar"),
			@ApiResponse(code = 404 , message = "Recurso não encontrado"),
			@ApiResponse(code = 505 , message = "Quando ocorre uma execessão")
	})
	public ResponseEntity<Veiculo> pegarVeiculo(@PathVariable Long id) {
		
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(veiculoRepository.findById(id).get());
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastra veículo", notes = "listagem de veículos")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Veículo adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
	})
	public ResponseEntity<Veiculo> inserir(@Valid @RequestBody Veiculo veiculo){
		return ResponseEntity.ok(veiculoRepository.save(veiculo));
	}
	
	@PutMapping("{id}")
	  @ApiOperation(value = "Atualiza veículo cadastrado", notes = "atualizar veículos")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Veículo atualizado"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
		})
	public ResponseEntity<Veiculo> atualizar(@Valid @RequestBody Veiculo veiculo, @PathVariable Long id){
		
		if(veiculoRepository.existsById(id)) {
			veiculo.setId(id);
			veiculo = veiculoRepository.save(veiculo);
			return ResponseEntity.ok(veiculo);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	 @ApiOperation(value = "Remove veículo cadastrado", notes = "remover veículo")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "Veículo removido"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Você não tem permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Ocorreu uma exceção"),
		})
	public ResponseEntity<Veiculo> deletar(@PathVariable Long id){
		
		if(veiculoRepository.existsById(id)) {
			
			veiculoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.noContent().build();
		
	}
}
