package org.serratec.backend.funcionario.controller;

import java.util.List;

import org.serratec.backend.funcionario.domain.Funcionario;
import org.serratec.backend.funcionario.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	private ResponseEntity<List<Funcionario>> lista(){
		
		List<Funcionario> listaFunc = funcionarioRepository.findAll();
		
		return ResponseEntity.ok(listaFunc);	
	}
	
	@GetMapping("{id}")
	private ResponseEntity<Funcionario> buscar(@PathVariable Long id){
		if(funcionarioRepository.existsById(id)) {
			return ResponseEntity.ok(funcionarioRepository.findById(id).get());
		}
		return ResponseEntity.notFound().build();	
	}
	
	@PostMapping
	private ResponseEntity<Funcionario> inserir(@RequestBody Funcionario funcionario){
		return ResponseEntity.ok(funcionarioRepository.save(funcionario));
	}
	
	@DeleteMapping("{id}")
	private ResponseEntity<Funcionario> deletar(@RequestBody Funcionario funcionario, @PathVariable Long id){
		if(funcionarioRepository.existsById(id)) {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	private ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario, @PathVariable Long id){
		if(funcionarioRepository.existsById(id)) {
			funcionario.setId(id);
			funcionario = funcionarioRepository.save(funcionario);
			
			return ResponseEntity.ok(funcionario);
		}
		return ResponseEntity.notFound().build();
	}
}
