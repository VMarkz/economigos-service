package br.com.economigos.service.dto.models.details;

import br.com.economigos.service.dto.models.GastoDto;
import br.com.economigos.service.model.Cartao;

import java.time.LocalDateTime;
import java.util.List;

public class DetalhesCartaoDto {
    private Long id;
    private String nome;
    private LocalDateTime fechamento;
    private LocalDateTime vencimento;
    private Boolean pago;
    private Double limite;
    private Double valor;
    private List<GastoDto> gastos;

    public DetalhesCartaoDto(Cartao cartao) {
        this.id = cartao.getId();
        this.nome = cartao.getNome();
        this.fechamento = cartao.getFechamento();
        this.vencimento = cartao.getVencimento();
        this.pago = cartao.getPago();
        this.limite = cartao.getLimite();
        this.valor = cartao.getValor();
        this.gastos = GastoDto.converter(cartao.getGastos());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDateTime fechamento) {
        this.fechamento = fechamento;
    }

    public LocalDateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDateTime vencimento) {
        this.vencimento = vencimento;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<GastoDto> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoDto> gastos) {
        this.gastos = gastos;
    }
}
