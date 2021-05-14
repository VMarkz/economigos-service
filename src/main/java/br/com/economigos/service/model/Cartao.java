package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDateTime fechamento;
    private LocalDateTime vencimento;
    private Boolean pago;
    private Double limite;
    private Double valor;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "cartao")
    private List<Gasto> gastos;

    public Cartao() {
    }

    public Cartao(Usuario usuario, String nome, LocalDateTime fechamento, LocalDateTime vencimento, Boolean pago,
                  Double limite) {
        this.usuario = usuario;
        this.nome = nome;
        this.fechamento = fechamento;
        this.vencimento = vencimento;
        this.pago = pago;
        this.limite = limite;
        this.valor = 0.0;
        this.gastos = new ArrayList<>();
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

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public LocalDateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDateTime vencimento) {
        this.vencimento = vencimento;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}

