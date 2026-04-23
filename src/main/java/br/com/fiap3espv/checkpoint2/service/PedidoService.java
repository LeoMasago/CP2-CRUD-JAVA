package br.com.fiap3espv.checkpoint2.service;

import br.com.fiap3espv.checkpoint2.model.Pedido;
import br.com.fiap3espv.checkpoint2.repository.PedidoRepository;
import br.com.fiap3espv.checkpoint2.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    //funções auxiliares para evitar redundância
    //verificando nome utilizando regex e lançando exceção caso caia no if
    private void validarNome(String nome) {
        if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            throw new IllegalArgumentException("O nome do cliente deve conter apenas letras");
        }
    }

    //verificando se o valor é negativo ou tem mais de 2 casas decimais
    private void validarValorTotal(Double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo");
        }
        if (new BigDecimal(Double.toString(valor)).scale() > 2) {
            throw new IllegalArgumentException("O valor total deve ter no máximo 2 casas decimais");
        }
    }

    //CRUD
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(String id) {
        //se o pedido existir, retorna, caso contrário, lança exceção
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));
    }

    public Pedido criar(Pedido pedido) {
        //fazendo todas as verificações e lançando exceções ao criar pedidos
        if (pedido.getClienteNome() == null || pedido.getClienteNome().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório");
        }
        validarNome(pedido.getClienteNome());
        if (pedido.getValorTotal() == null) {
            throw new IllegalArgumentException("O valor total é obrigatório");
        }
        validarValorTotal(pedido.getValorTotal());
        pedido.setDataPedido(LocalDate.now());
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(String id, Pedido pedidoAtualizado) {
        //se o pedido existir, retorna, caso contrário, lança exceção
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com id: " + id));

        //fazendo as verificações e retornando as exceções
        if (pedidoAtualizado.getClienteNome() != null) {
            if (pedidoAtualizado.getClienteNome().isBlank()) {
                throw new IllegalArgumentException("O nome do cliente não pode ser vazio");
            }
            validarNome(pedidoAtualizado.getClienteNome());
            pedido.setClienteNome(pedidoAtualizado.getClienteNome());
        }
        if (pedidoAtualizado.getValorTotal() != null) {
            validarValorTotal(pedidoAtualizado.getValorTotal());
            pedido.setValorTotal(pedidoAtualizado.getValorTotal());
        }

        return pedidoRepository.save(pedido);
    }

    public void deletar(String id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrado com id: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
