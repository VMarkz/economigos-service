package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String banco;
    private String numeroConta;
    private String descricao;
    private String apelido;
    @OneToMany
    private List<Renda> rendas;

    public Conta(){
    }

    public Conta(String banco, String numeroConta, String descricao, String apelido) {
        this.id = id;
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.descricao = descricao;
        this.apelido = apelido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
