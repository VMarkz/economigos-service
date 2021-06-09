package br.com.economigos.service.dto.models.details;

import br.com.economigos.service.dto.models.CategoriaDto;
import br.com.economigos.service.dto.models.ContaDto;
import br.com.economigos.service.model.Renda;

public class DetalhesRendaDto {

    private Long id;
    private ContaDto conta;
    private Double valor;
    private String descricao;
    private String dataPagamento;
    private Boolean fixo;
    private CategoriaDto categoria;

    public DetalhesRendaDto(Renda renda) {
        this.id = renda.getId();
        this.conta = new ContaDto(renda.getConta());
        this.valor = renda.getValor();
        this.descricao = renda.getDescricao();
        this.dataPagamento = renda.getDataPagamento();
        this.fixo = renda.getFixo();
        this.categoria = new CategoriaDto(renda.getCategoria());
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

    public String getData() {
        return dataPagamento;
    }

    public void setData(String data) {
        this.dataPagamento = data;
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
