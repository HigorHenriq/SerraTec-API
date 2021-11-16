package org.serratec.backend.projetoDt.controller;

import java.util.List;

import org.serratec.backend.projetoDt.dto.UsuarioDTO;
import org.serratec.backend.projetoDt.dto.UsuarioInserirDTO;
import org.serratec.backend.projetoDt.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/dt")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar(){
		List<UsuarioDTO> usuarios = usuarioService.pesquisarTodos();
		return ResponseEntity.ok(usuarios);
	}
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO, UriComponentsBuilder b) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
		UriComponents uriComponents = b.path("/usuarios/{id}").buildAndExpand(usuarioDTO.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(usuarioDTO);
	}
}
