package br.com.vitor.usercrud.controler.dto;

import br.com.vitor.usercrud.model.Categoria;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;

import java.time.LocalDateTime;

public class DetalhesGastoDto {

    private Long id;
    private Conta conta;
    private Double valor;
    private String descricao;
    private LocalDateTime dataPagamento;
    private Boolean fixo;
    private Categoria categoria;

    public DetalhesGastoDto(Gasto gasto) {
        this.id = gasto.getId();
        this.conta = gasto.getConta();
        this.valor = gasto.getValor();
        this.descricao = gasto.getDescricao();
        this.dataPagamento = gasto.getDataPagamento();
        this.fixo = gasto.getFixo();
        this.categoria = gasto.getCategoria();
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

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
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
