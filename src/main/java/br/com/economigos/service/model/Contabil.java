package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Observable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Contabil extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String tipo;
    @ManyToOne
    protected Conta conta;
    @ManyToOne
    protected Categoria categoria;
    protected Double valor;
    protected String descricao;
    protected String dataPagamento;
    protected Boolean fixo;

    public Contabil(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, String dataPagamento,String tipo) {
        this.conta = conta;
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.dataPagamento = dataPagamento;
        this.fixo = fixo;
        this.tipo = tipo;
    }

    public Contabil(Categoria categoria, Double valor, String descricao, Boolean fixo, String dataPagamento, String tipo) {
        this.categoria = categoria;
        this.valor = valor;
        this.descricao = descricao;
        this.dataPagamento = dataPagamento;
        this.fixo = fixo;
        this.tipo = tipo;
    }

    public Contabil() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public void notificaObservador(String acao){
        setChanged();
        notifyObservers(acao);
    }

}
