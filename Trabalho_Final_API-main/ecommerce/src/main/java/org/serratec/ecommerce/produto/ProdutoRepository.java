package org.serratec.ecommerce.produto;

import org.serratec.ecommerce.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeIgnoreCase(String nome);
}
