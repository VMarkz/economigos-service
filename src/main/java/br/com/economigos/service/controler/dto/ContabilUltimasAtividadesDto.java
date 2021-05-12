package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Gasto;

public class ContabilUltimasAtividadesDto {
    private String descricao;
    private String data;
    private Double valor;
    private String tipo;

    public ContabilUltimasAtividadesDto() {
    }

    public ContabilUltimasAtividadesDto(String descricao, String data, Double valor, String tipo) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
