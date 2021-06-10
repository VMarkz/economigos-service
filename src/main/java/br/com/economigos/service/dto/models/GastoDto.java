package br.com.economigos.service.dto.models;

import br.com.economigos.service.model.Gasto;

import java.util.List;
import java.util.stream.Collectors;

public class GastoDto {

    private Long id;
    private Double valor;
    private Boolean pago;
    private String descricao;
    private String dataPagamento;
    private CategoriaDto categoria;

    public GastoDto(Gasto gasto) {
        this.id = gasto.getId();
        this.valor = gasto.getValor();
        this.pago = gasto.getPago();
        this.descricao = gasto.getDescricao();
        this.dataPagamento = gasto.getDataPagamento();
        this.categoria = new CategoriaDto(gasto.getCategoria());
    }

    public static List<GastoDto> converter(List<Gasto> gastos) {
        return gastos.stream().map(GastoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
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
}
