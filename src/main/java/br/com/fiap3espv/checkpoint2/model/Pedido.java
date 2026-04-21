package br.com.fiap3espv.checkpoint2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String clienteNome;

    private LocalDate dataPedido = LocalDate.now();

    @NotNull(message = "O valor total é obrigatório")
    @PositiveOrZero(message = "O valor total não pode ser negativo")
    private Double valorTotal;
}
