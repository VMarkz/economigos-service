package br.com.economigos.service.controler.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContabilUltimasAtividadesDto implements Comparable<ContabilUltimasAtividadesDto>{
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

    @Override
    public int compareTo(ContabilUltimasAtividadesDto o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formatter = formatter.withLocale(Locale.ENGLISH);

        LocalDateTime thisRendaDate = LocalDateTime.parse(this.getData(), formatter);
        LocalDateTime outraRendaDate = LocalDateTime.parse(o.getData(), formatter);

        if (thisRendaDate.isAfter(outraRendaDate)) {
            return -1;
        } if (thisRendaDate.isBefore(outraRendaDate)) {
            return 1;
        }
        return 0;
    }
}
