package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.GastoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GastoForm implements CommonForm{


    private String conta;

    private Double valor;
    @NotNull
    private Boolean pago;

    private String descricao;

    private Boolean fixo;

    private String categoria;

    private LocalDateTime dataPagamento;

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Gasto converter(ContaRepository contaRepository, CategoriaRepository categoriaRepository) {
        Conta conta = contaRepository.findByApelido(this.conta);
        Categoria categoria = categoriaRepository.findByCategoria(this.categoria);
        return new Gasto(conta, categoria, this.valor,this.descricao,this.dataPagamento,this.fixo, this.pago);
    }

    public Gasto atualizar(Long id, GastoRepository gastoRepository) {
        Gasto gasto = gastoRepository.getOne(id);

        gasto.setDescricao(this.descricao);
        gasto.setFixo(this.fixo);
        gasto.setValor(this.valor);
        gasto.setDataPagamento(this.dataPagamento);

        return gasto;
    }

}
