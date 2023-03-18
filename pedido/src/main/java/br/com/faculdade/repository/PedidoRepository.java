package br.com.faculdade.repository;

import br.com.faculdade.model.Pedido;
import br.com.faculdade.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
