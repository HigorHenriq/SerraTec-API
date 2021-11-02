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

@RestController
@RequestMapping("/h2veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public ResponseEntity<List<Veiculo>> Lista(){
		List<Veiculo> listaVeiculos = veiculoRepository.findAll();
		return ResponseEntity.ok(listaVeiculos);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Veiculo> pegarVeiculo(@PathVariable Long id) {
		
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(veiculoRepository.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Veiculo> inserir(@Valid @RequestBody Veiculo veiculo){
		return ResponseEntity.ok(veiculoRepository.save(veiculo));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Veiculo> atualizar(@Valid @RequestBody Veiculo veiculo, @PathVariable Long id){
		
		if(veiculoRepository.existsById(id)) {
			veiculo.setId(id);
			veiculo = veiculoRepository.save(veiculo);
			return ResponseEntity.ok(veiculo);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Veiculo> deletar(@PathVariable Long id){
		
		if(veiculoRepository.existsById(id)) {
			
			veiculoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.noContent().build();
		
	}
}
