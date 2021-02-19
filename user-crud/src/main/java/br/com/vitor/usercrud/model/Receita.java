package br.com.vitor.usercrud.model;

import java.time.LocalDateTime;

public abstract class Receita {
    private Double valor;
    private String descricao;
    private LocalDateTime data;
    private Boolean fixo;
    private Categoria categoria;

    public Receita(Double valor, String descricao, LocalDateTime data, Boolean fixo, Categoria categoria) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
        this.fixo = fixo;
        this.categoria = categoria;
    }

    public Receita() {
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
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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
