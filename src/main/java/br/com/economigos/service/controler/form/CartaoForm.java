package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.repository.CartaoRepository;
import br.com.economigos.service.repository.ContaRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CartaoForm implements CommonForm {
    @NotNull
    private String nome;
    @NotNull
    private LocalDateTime fechamento;
    @NotNull
    private LocalDateTime vencimento;
    @NotNull
    private Boolean pago;
    @NotNull
    private Double limite;

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

    public Cartao converter() {
        return (new Cartao(this.nome, this.fechamento, this.vencimento, this.pago, this.limite));
    }

    public Cartao atualizar(Long id, CartaoRepository cartaoRepository){
        Cartao cartao = cartaoRepository.getOne(id);

        cartao.setNome(this.nome);
        cartao.setFechamento(this.fechamento);
        cartao.setVencimento(this.vencimento);
        cartao.setPago(this.pago);
        cartao.setLimite(this.limite);


        return cartao;
    }
}
