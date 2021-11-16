package org.serratec.backend.projetoDt.repository;

import java.util.Optional;

import org.serratec.backend.projetoDt.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	Optional<Usuario> findByNome(String nome);
}
