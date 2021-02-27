package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Renda{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Conta conta;
    private Double valor;
    private String descricao;
    private LocalDateTime dataRecebimento;
    private Boolean fixo;
    @ManyToOne
    private Categoria categoria;

    public Renda(Conta conta, Double valor, String descricao, LocalDateTime dataRecebimento, Boolean fixo, Categoria categoria) {
        this.conta = conta;
        this.valor = valor;
        this.descricao = descricao;
        this.dataRecebimento = dataRecebimento;
        this.fixo = fixo;
        this.categoria = categoria;
    }

    public Renda() {
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
        return dataRecebimento;
    }

    public void setData(LocalDateTime data) {
        this.dataRecebimento = data;
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
