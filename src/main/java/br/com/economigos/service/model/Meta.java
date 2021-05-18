package br.com.economigos.service.model;

import javax.persistence.*;
import java.util.Observable;

@Entity
public class Meta extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean metaGasto;
    private Double valorAtual;
    private Double valorFinal;
    @ManyToOne
    private Usuario usuario;
    private Boolean ativa;
    private Boolean finalizada;

    public Meta(String nome, String descricao, Boolean metaGasto, Double valorAtual, Double valorFinal, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.metaGasto = metaGasto;
        this.valorAtual = valorAtual;
        this.valorFinal = valorFinal;
        this.usuario = usuario;
        this.ativa = true;
        this.finalizada = false;
    }

    public Meta() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getMetaGasto() {
        return metaGasto;
    }

    public void setMetaGasto(Boolean metaGasto) {
        this.metaGasto = metaGasto;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public void notificaObservador(String acao) {
        setChanged();
        notifyObservers(acao);
    }
}
