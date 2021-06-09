package br.com.economigos.service.dto.models.details;

import br.com.economigos.service.dto.models.GastoDto;
import br.com.economigos.service.dto.models.RendaDto;
import br.com.economigos.service.model.Conta;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesContaDto {

    private Long id;
    private String banco;
    private String numeroConta;
    private String descricao;
    private String apelido;
    private Double valorAtual;
    private Double totalRendas;
    private Double totalGastos;
    private List<RendaDto> rendas;
    private List<GastoDto> gastos;


    public DetalhesContaDto(Conta conta) {
        this.id = conta.getId();
        this.banco = conta.getBanco();
        this.numeroConta = conta.getNumeroConta();
        this.descricao = conta.getDescricao();
        this.apelido = conta.getApelido();
        this.valorAtual = conta.getValorAtual();
        this.rendas = RendaDto.converter(conta.getRendas());
        this.gastos = GastoDto.converter(conta.getGastos());
        this.totalRendas = 0.0;
        this.totalGastos = 0.0;
    }

    public static List<DetalhesContaDto> converter(List<Conta> contas) {
        return contas.stream().map(DetalhesContaDto::new).collect(Collectors.toList());
    }

    public Double getTotalRendas() {
        return totalRendas;
    }

    public void setTotalRendas(Double totalRendas) {
        this.totalRendas = totalRendas;
    }

    public Double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(Double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
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

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String nome) {
        this.apelido = apelido;
    }

    public List<RendaDto> getRendas() {
        return rendas;
    }

    public void setRendas(List<RendaDto> rendas) {
        this.rendas = rendas;
    }

    public List<GastoDto> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoDto> gastos) {
        this.gastos = gastos;
    }

}
