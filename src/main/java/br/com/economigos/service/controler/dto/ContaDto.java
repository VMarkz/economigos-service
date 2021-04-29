package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Conta;

import java.util.List;
import java.util.stream.Collectors;

public class ContaDto implements CommonDto{

    private Long id;
    private String banco;
    private String apelido;
    private Double valorAtual;

    public ContaDto(Conta conta) {
        this.id = conta.getId();
        this.banco = conta.getBanco();
        this.apelido = conta.getApelido();
        this.valorAtual = conta.getValorAtual();
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public static List<ContaDto> converter(List<Conta> contas) {
        return contas.stream().map(ContaDto::new).collect(Collectors.toList());
    }

}
