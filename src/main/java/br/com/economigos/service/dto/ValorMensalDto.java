package br.com.economigos.service.dto;

public class ValorMensalDto {

    private String mes;
    private Double valor;

    public ValorMensalDto(String mes, Double valor) {
        this.mes = mes;
        this.valor = valor;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
