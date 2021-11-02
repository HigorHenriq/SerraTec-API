package org.serratec.backend.h2banco.repository;

import org.serratec.backend.h2banco.domain.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{

}
