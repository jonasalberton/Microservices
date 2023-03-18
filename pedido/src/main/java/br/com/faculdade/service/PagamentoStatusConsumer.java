package br.com.faculdade.service;

import br.com.faculdade.model.PagamentoStatus;
import br.com.faculdade.model.Pedido;
import br.com.faculdade.repository.PedidoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoStatusConsumer {

    @Autowired
    private PedidoRepository pedidoRepository;

    @RabbitListener(queues = "${pagamento-status.rabbitmq.queue}")
    public void receivePagamentoStatus(PagamentoStatus pagamentoStatus) {
        System.out.println("Pedido recebido, id: " + pagamentoStatus.getIdPedido());

        Pedido pedido = pedidoRepository.findById(pagamentoStatus.getIdPedido()).get();

        pedido.setStatus(pagamentoStatus.getStatus());
        System.out.println("Pagamento com status: " + pagamentoStatus.getStatus());

        pedidoRepository.save(pedido);
    }
}