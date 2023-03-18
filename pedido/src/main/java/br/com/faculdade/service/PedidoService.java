package br.com.faculdade.service;

import br.com.faculdade.exception.PessoaNotFoundException;
import br.com.faculdade.model.Pedido;
import br.com.faculdade.model.Pessoa;
import br.com.faculdade.repository.PedidoRepository;
import br.com.faculdade.repository.PessoaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${pedido.rabbitmq.queue}")
    private String queue;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void sendPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(queue, pedido);
    }
    public Pedido salvar(Pedido pedido) throws PessoaNotFoundException{
        if (!pessoaRepository.findById(pedido.getIdPessoa()).isPresent()){
            throw new PessoaNotFoundException("Pessoa não encontrada com ID: " + pedido.getIdPessoa());
        }
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        sendPedido(pedidoSalvo);
        return pedidoSalvo;
    }
}