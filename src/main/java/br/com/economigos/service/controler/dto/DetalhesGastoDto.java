package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;

import java.time.LocalDateTime;

public class DetalhesGastoDto {

    private Long id;
    private ContaDto conta;
    private Double valor;
    private String descricao;
    private LocalDateTime dataPagamento;
    private Boolean fixo;
    private CategoriaDto categoria;

    public DetalhesGastoDto(Gasto gasto) {
        this.id = gasto.getId();
        this.conta = new ContaDto(gasto.getConta());
        this.valor = gasto.getValor();
        this.descricao = gasto.getDescricao();
        this.dataPagamento = gasto.getDataPagamento();
        this.fixo = gasto.getFixo();
        this.categoria = new CategoriaDto(gasto.getCategoria());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContaDto getConta() {
        return conta;
    }

    public void setConta(ContaDto conta) {
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

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }
}
