package org.serratec.backend.projetoDt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.projetoDt.domain.Usuario;
import org.serratec.backend.projetoDt.domain.UsuarioPerfil;
import org.serratec.backend.projetoDt.dto.UsuarioDTO;
import org.serratec.backend.projetoDt.dto.UsuarioInserirDTO;
import org.serratec.backend.projetoDt.exception.EmailException;
import org.serratec.backend.projetoDt.repository.UsuarioPerfilRepository;
import org.serratec.backend.projetoDt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	private UsuarioPerfilRepository usuarioPerfilRepository;
	
	
	public List<UsuarioDTO> pesquisarTodos(){
		List<Usuario> usuarios =  usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		
		for(Usuario usuario : usuarios) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}
	
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException{
		Usuario usuario = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if(usuario != null) {
			throw new EmailException("Email já existente");
		}
		
		for(UsuarioPerfil usuarioPerfil : usuarioInserirDTO.getUsuarioPerfis()) {
			usuarioPerfil.setUsuario(usuario);
			usuarioPerfil.setPerfil(perfilService.buscar(usuarioPerfil.getPerfil().getId()));
			usuarioPerfil.setDataCriacao(LocalDate.now());
		}
		
		usuarioPerfilRepository.saveAll(usuarioInserirDTO.getUsuarioPerfis());
		
		usuarioInserirDTO.setSenha(passwordEncoder.encode(usuarioInserirDTO.getSenha()));
		return new UsuarioDTO(usuario);
	}
}
