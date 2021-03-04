package br.com.vitor.usercrud.controler.dto;

import br.com.vitor.usercrud.model.Renda;
import br.com.vitor.usercrud.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class RendaDto implements CommonDto{

    private Long id;
    private Double valor;
    private String descricao;

    public RendaDto(Renda renda) {
        this.id = renda.getId();
        this.valor = renda.getValor();
        this.descricao = renda.getDescricao();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static List<RendaDto> converter(List<Renda> rendas) {
        return rendas.stream().map(RendaDto::new).collect(Collectors.toList());
    }

}
