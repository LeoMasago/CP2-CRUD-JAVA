package br.com.fiap3espv.checkpoint2.service;

import br.com.fiap3espv.checkpoint2.model.Pedido;
import br.com.fiap3espv.checkpoint2.repository.PedidoRepository;
import br.com.fiap3espv.checkpoint2.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

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
        if (pedido.getValorTotal() == null) {
            throw new IllegalArgumentException("O valor total é obrigatório");
        }
        if (pedido.getValorTotal() < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo");
        }
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
            pedido.setClienteNome(pedidoAtualizado.getClienteNome());
        }
        if (pedidoAtualizado.getValorTotal() != null) {
            if (pedidoAtualizado.getValorTotal() < 0) {
                throw new IllegalArgumentException("O valor total não pode ser negativo");
            }
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
