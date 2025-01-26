package com.dev.SpringAPI.controller.dto;


import java.math.BigDecimal;

public class CreatePedidoDto {

    private String nome;

    private String descricao;

    private BigDecimal valorTotal;

    private String status;

    public CreatePedidoDto() {
    }

    public CreatePedidoDto(String nome, String descricao, BigDecimal valorTotal, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}