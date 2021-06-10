package br.com.economigos.service.model;

import br.com.economigos.service.repository.GastoRepository;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate fechamento;
    private LocalDate vencimento;
    private Boolean pago;
    private Double limite;
    private Double valor;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "cartao")
    private List<Gasto> gastos;

    public Cartao() {
    }

    public Cartao(Usuario usuario, String nome, LocalDate fechamento, LocalDate vencimento, Boolean pago, Double limite) {
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

    public LocalDate getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDate fechamento) {
        this.fechamento = fechamento;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
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

    public Cartao setValorFatura(Cartao cartao, GastoRepository gastoRepository){
        if (!cartao.getPago()){
            Integer diaFechamento = cartao.getFechamento().getDayOfMonth();
            Integer diaAtual = LocalDate.now().getDayOfMonth();
            Integer mesAtual = LocalDate.now().getMonth().getValue();
            Integer mesComparacao = 0;
            String data1 ="";
            String data2 = "";
            Integer anoAtual = LocalDate.now().getYear();
            Double somaGastosCartao = 0.0;
            if (diaAtual > diaFechamento){
                mesComparacao = mesAtual + 1;
                data1 = String.format("%d-%02d-%02d", anoAtual,mesAtual,diaFechamento);
                data2 = String.format("%d-%02d-%02d", anoAtual,mesComparacao,diaFechamento);
                somaGastosCartao = cartao.getValor() + gastoRepository.somaGastosCartao(id, data1, data2);
            } else {
                mesComparacao = mesAtual - 1;
                data1 = String.format("%d-%02d-%02d", anoAtual,mesComparacao,diaFechamento);
                data2 = String.format("%d-%02d-%02d", anoAtual,mesAtual,diaFechamento);
                somaGastosCartao = cartao.getValor() + gastoRepository.somaGastosCartao(id, data1, data2);
            }
            if (somaGastosCartao != null){
                cartao.setValor(somaGastosCartao);
            }
            return cartao;
        } else {
            return cartao;
        }

    }
}

