package br.com.faculdade.controller;

import br.com.faculdade.model.Pedido;
import br.com.faculdade.model.Pessoa;
import br.com.faculdade.repository.PedidoRepository;
import br.com.faculdade.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    // Criação de uma pessoa
    @PostMapping()
    public ResponseEntity<Pedido> createPessoa(@RequestBody Pedido pedido) {
        try {
            Pedido _pedido = pedidoRepository.save(pedido);
            pedidoService.sendPedido(pedido);
            return new ResponseEntity<>(_pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Busca de pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPessoaById(@PathVariable("id") long id) {
        Optional<Pedido> pedidoData = pedidoRepository.findById(id);

        if (pedidoData.isPresent()) {
            return new ResponseEntity<>(pedidoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
