package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.util.ArrayList;
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
    @OneToMany
    private List<Gasto> gastos;

    public Conta(){
    }

    public Conta(String banco, String numeroConta, String descricao, String apelido) {
        this.id = id;
        this.banco = banco;
        this.numeroConta = numeroConta;
        this.descricao = descricao;
        this.apelido = apelido;
        rendas = new ArrayList<>();
        gastos = new ArrayList<>();
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

    public List<Renda> getRendas() {
        return rendas;
    }

    public void setRendas(List<Renda> rendas) {
        this.rendas = rendas;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void adcionarGasto(Gasto gasto){
        gastos.add(gasto);
    }

    public void adcionarRenda(Renda renda){
        rendas.add(renda);
    }
}
