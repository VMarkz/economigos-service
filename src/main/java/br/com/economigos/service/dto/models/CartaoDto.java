package br.com.economigos.service.dto.models;

import br.com.economigos.service.model.Cartao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CartaoDto{

    private Long id;
    private String nome;
    private LocalDateTime vencimento;
    private Double valor;

    public CartaoDto(Cartao cartao) {
        this.id = cartao.getId();
        this.nome = cartao.getNome();
        this.vencimento = cartao.getVencimento();
        this.valor = cartao.getValor();
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

    public LocalDateTime getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDateTime vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public static List<CartaoDto> converter(List<Cartao> cartoes) {
        return cartoes.stream().map(CartaoDto::new).collect(Collectors.toList());
    }
}
