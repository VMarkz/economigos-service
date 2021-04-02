package br.com.economigos.service.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Entity
public class Conta extends Observable implements Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String banco;
    private String numeroConta;
    private String descricao;
    private String apelido;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "conta")
    private List<Renda> rendas;
    @OneToMany(mappedBy = "conta")
    private List<Gasto> gastos;

    public Conta() {
    }

    public Conta(String banco, String numeroConta, String descricao, String apelido) {
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

    public void adcionarGasto(Gasto gasto) {
        gastos.add(gasto);
    }

    public void adcionarRenda(Renda renda) {
        rendas.add(renda);
    }

    @Override
    public void update(Observable o, Object arg) {
        String acao = String.valueOf(arg);

        if (o.getClass().equals(Gasto.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        } else if (o.getClass().equals(Renda.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        }
    }

    public void mudaEstado(String acao){
        setChanged();
        notifyObservers(acao);
    }
}
