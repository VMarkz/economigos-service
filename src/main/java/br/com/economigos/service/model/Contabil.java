package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Observable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("P")
public abstract class Contabil extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(insertable = false, updatable = false)
    protected String tipo;
    @ManyToOne
    protected Conta conta;
    protected Double valor;
    protected String descricao;
    protected LocalDateTime dataPagamento;
    protected Boolean fixo;
    @ManyToOne
    protected Categoria categoria;

    public Contabil(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, LocalDateTime dataPagamento) {
        this.conta = conta;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.dataPagamento = LocalDateTime.now();
        this.fixo = fixo;
        this.dataPagamento = dataPagamento;
    }

    public Contabil(Categoria categoria, Double valor, String descricao, Boolean fixo, LocalDateTime dataPagamento) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.dataPagamento = LocalDateTime.now();
        this.fixo = fixo;
        this.dataPagamento = dataPagamento;
    }

    public Contabil() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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

    public void notificaObservador(String acao){
        setChanged();
        notifyObservers(acao);
    }

}
