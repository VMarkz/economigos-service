package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Renda;

import java.time.LocalDateTime;

public class DetalhesRendaDto {

    private Long id;
    private Conta conta;
    private Double valor;
    private String descricao;
    private LocalDateTime dataPagamento;
    private Boolean fixo;
    private Categoria categoria;

    public DetalhesRendaDto(Renda renda) {
        this.id = renda.getId();
        this.conta = renda.getConta();
        this.valor = renda.getValor();
        this.descricao = renda.getDescricao();
        this.dataPagamento = renda.getDataPagamento();
        this.fixo = renda.getFixo();
        this.categoria = renda.getCategoria();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return dataPagamento;
    }

    public void setData(LocalDateTime data) {
        this.dataPagamento = data;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
