package br.com.faculdade.service;

import br.com.faculdade.model.Pedido;
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

    public void sendPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(queue, pedido);
    }
}