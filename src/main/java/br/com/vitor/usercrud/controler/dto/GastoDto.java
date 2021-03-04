package br.com.vitor.usercrud.controler.dto;

import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;

import java.util.List;
import java.util.stream.Collectors;

public class GastoDto implements CommonDto{

    private Long id;
    private Double valor;
    private Boolean pago;
    private String descricao;

    public GastoDto(Gasto gasto) {
        this.valor = gasto.getValor();
        this.pago = gasto.getPago();
        this.descricao = gasto.getDescricao();
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

    public static List<GastoDto> converter(List<Gasto> gastos) {
       return gastos.stream().map(GastoDto::new).collect(Collectors.toList());
    }

}
