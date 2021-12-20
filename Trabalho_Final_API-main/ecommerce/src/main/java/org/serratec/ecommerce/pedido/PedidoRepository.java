package org.serratec.ecommerce.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //Optional<Veiculo> findByNomeIgnoreCase(String nome);
//    Optional<Cliente> findByCliente_Id(Long id);
//    boolean existsByCliente_Id(Long id);
//    boolean existsByCategoria_Id(Long id);
}
