package org.serratec.java2backend.exercicio02.repository;

import org.serratec.java2backend.exercicio02.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//A JpaRepo Ela tem todos os métodos que a gente precisa para fazer um CRUD (criar, ler, atualizar, deletar).
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{

}
