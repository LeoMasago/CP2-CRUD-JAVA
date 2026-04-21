package br.com.fiap3espv.checkpoint2.repository;

import br.com.fiap3espv.checkpoint2.model.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
