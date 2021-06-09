package br.com.economigos.service.dto.models.details;

import br.com.economigos.service.dto.models.CategoriaDto;
import br.com.economigos.service.dto.models.ContaDto;
import br.com.economigos.service.model.Gasto;

public class DetalhesGastoDto {

    private Long id;
    private ContaDto conta;
    private Double valor;
    private String descricao;
    private String dataPagamento;
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
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
