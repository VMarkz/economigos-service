package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.GastoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GastoForm implements CommonForm{

    @NotEmpty
    @NotNull
    private Conta conta;
    @NotEmpty
    @NotNull
    private Double valor;
    @NotNull
    private Boolean pago;
    @NotEmpty
    @NotNull
    private String descricao;
    @NotEmpty
    @NotNull
    private Boolean fixo;
    @NotEmpty
    @NotNull
    private Categoria categoria;
    @NotEmpty
    @NotNull
    private LocalDateTime dataPagamento;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
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

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public Gasto converter() {
        return new Gasto(this.valor,this.descricao,this.dataPagamento,this.fixo,this.categoria, this.pago);
    }

    public Gasto atualizar(Long id, GastoRepository gastoRepository) {
        Gasto gasto = gastoRepository.getOne(id);

        gasto.setConta(this.conta);
        gasto.setCategoria(this.categoria);
        gasto.setDescricao(this.descricao);
        gasto.setFixo(this.fixo);
        gasto.setValor(this.valor);
        gasto.setDataPagamento(this.dataPagamento);

        return gasto;
    }
}
