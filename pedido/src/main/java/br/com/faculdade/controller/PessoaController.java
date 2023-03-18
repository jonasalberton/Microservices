package br.com.faculdade.controller;

import br.com.faculdade.model.Pessoa;
import br.com.faculdade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    // Criação de uma pessoa
    @PostMapping()
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa _pessoa = pessoaRepository.save(pessoa);
            return new ResponseEntity<>(_pessoa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Busca de pessoa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") long id) {
        Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

        if (pessoaData.isPresent()) {
            return new ResponseEntity<>(pessoaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
