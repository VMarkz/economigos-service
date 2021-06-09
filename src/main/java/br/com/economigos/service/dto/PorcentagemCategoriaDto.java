package br.com.economigos.service.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PorcentagemCategoriaDto {

    private String nome;
    private Double soma;
    private Double porcentagem;

    public PorcentagemCategoriaDto(String nome,Double soma, Double porcentagem) {
        this.nome = nome;
        this.soma = soma;
        this.porcentagem = porcentagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSoma() {
        return soma;
    }

    public void setSoma(Double soma) {
        this.soma = soma;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

}
