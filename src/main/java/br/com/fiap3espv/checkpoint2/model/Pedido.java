package br.com.fiap3espv.checkpoint2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;

    private String clienteNome;

    private LocalDate dataPedido = LocalDate.now();

    private Double valorTotal;
}
