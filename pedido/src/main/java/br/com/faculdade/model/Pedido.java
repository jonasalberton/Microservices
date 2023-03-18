package br.com.faculdade.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "id_produto")
    private String idProduto;

    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(name = "status")
    private String status;

    @Column(name = "tipo_pagamento")
    private String tipoPagamento;

    public Pedido() {
    }

    public Pedido(Integer quantidade, String idProduto, Long idPessoa) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.idPessoa = idPessoa;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}