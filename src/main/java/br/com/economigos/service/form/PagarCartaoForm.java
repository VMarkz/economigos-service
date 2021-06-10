package br.com.economigos.service.controler.form;

import javax.validation.constraints.NotNull;

public class PagarCartaoForm {

    @NotNull
    private Long idCartao;
    @NotNull
    private Long idConta;
    @NotNull
    private Double valor;

    public PagarCartaoForm(Long idCartao, Long idConta, Double valor) {
        this.idCartao = idCartao;
        this.idConta = idConta;
        this.valor = valor;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
