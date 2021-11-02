package org.serratec.backend.h2banco.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.backend.h2banco.domain.Proprietario;
import org.serratec.backend.h2banco.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

	@Autowired
	private ProprietarioRepository proprietarioRepository;
	
	
	@GetMapping
	public ResponseEntity<List<Proprietario>> listar(){
		List<Proprietario> proprietarios = proprietarioRepository.findAll();
		return ResponseEntity.ok(proprietarios);
	}
	
	//METODO PARA INSERIR UM PROPRIETARIO POR VEZ
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Proprietario inserir(@RequestBody Proprietario proprietario) {
		return proprietarioRepository.save(proprietario);	
	}
	
	//METODO PARA INSERIR VARIOS DE UMA VEZ SÓ
	//PARA USAR NO POST BATA PASSAR A HTTP COM /LISTA
	@PostMapping("/lista")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Proprietario> inserirVarios(@Valid @RequestBody List<Proprietario> proprietarios){
		return proprietarioRepository.saveAll(proprietarios);
	}
}
