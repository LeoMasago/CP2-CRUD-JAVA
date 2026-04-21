package br.com.fiap3espv.checkpoint2.service;

import br.com.fiap3espv.checkpoint2.model.Pedido;
import br.com.fiap3espv.checkpoint2.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    public Pedido criar(Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> atualizar(String id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            if (pedidoAtualizado.getClienteNome() != null) {
                pedido.setClienteNome(pedidoAtualizado.getClienteNome());
            }
            if (pedidoAtualizado.getValorTotal() != null) {
                pedido.setValorTotal(pedidoAtualizado.getValorTotal());
            }
            return pedidoRepository.save(pedido);
        });
    }

    public boolean deletar(String id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
