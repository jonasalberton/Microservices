package br.com.faculdade.controller;

import br.com.faculdade.exception.PessoaNotFoundException;
import br.com.faculdade.model.Pedido;
import br.com.faculdade.repository.PedidoRepository;
import br.com.faculdade.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido _pedido = pedidoService.salvar(pedido);
            return new ResponseEntity<>(_pedido, HttpStatus.CREATED);
        } catch (PessoaNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("id") long id) {
        Optional<Pedido> pedidoData = pedidoRepository.findById(id);

        if (pedidoData.isPresent()) {
            return new ResponseEntity<>(pedidoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidoData = pedidoRepository.findAll();
        if (!pedidoData.isEmpty()) {
            return new ResponseEntity<>(pedidoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
