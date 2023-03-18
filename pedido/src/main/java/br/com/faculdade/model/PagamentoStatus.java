package br.com.faculdade.model;

import java.io.Serializable;

public class PagamentoStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPedido;
    private String status;

    public PagamentoStatus() {
    }

    public PagamentoStatus(Long idPedido, String status) {
        this.idPedido = idPedido;
        this.status = status;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}