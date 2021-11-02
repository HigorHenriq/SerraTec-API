package org.serratec.backend.h2banco.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.backend.h2banco.domain.Manutencao;
import org.serratec.backend.h2banco.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manutencoes")
public class ManutencaoController {

	@Autowired
	private ManutencaoRepository manutencaoRepository;

	@GetMapping
	public ResponseEntity<List<Manutencao>> listar(){
		List<Manutencao> manutencoes = manutencaoRepository.findAll();
		return ResponseEntity.ok(manutencoes);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Manutencao> listarUm(@PathVariable Long id){
		Optional<Manutencao> manutencao = manutencaoRepository.findById(id);
		if(manutencao.isPresent()) {
			return ResponseEntity.ok(manutencao.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("{id}")
	public ResponseEntity<Manutencao> inserir(@RequestBody Manutencao manutencao, @PathVariable Long id){	
		return ResponseEntity.ok(manutencaoRepository.save(manutencao));
	}
}
