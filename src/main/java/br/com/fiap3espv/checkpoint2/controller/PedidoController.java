package br.com.fiap3espv.checkpoint2.controller;

import br.com.fiap3espv.checkpoint2.model.Pedido;
import br.com.fiap3espv.checkpoint2.service.PedidoService;
import br.com.fiap3espv.checkpoint2.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        try {
            return ResponseEntity.ok(pedidoService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(pedidoService.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            //retorna 404 se o pedido não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Pedido pedido) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(pedido));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.atualizar(id, pedido));
        } catch (EntityNotFoundException e) {
            //retorna 404 se o pedido não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        try {
            pedidoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            //retorna 404 se o pedido não existir
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
