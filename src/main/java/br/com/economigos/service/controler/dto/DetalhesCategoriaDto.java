package br.com.vitor.usercrud.controler.dto;

import br.com.vitor.usercrud.model.Categoria;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;

import java.util.List;
import java.util.stream.Collectors;

public class DetalhesCategoriaDto implements CommonDto{

    private Long id;
    private String categoria;
    private List<GastoDto> gastos;
    private List<RendaDto> rendas;

    public DetalhesCategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.categoria = categoria.getCategoria();
        this.rendas = RendaDto.converter(categoria.getRendas());
        this.gastos = GastoDto.converter(categoria.getGastos());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<GastoDto> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoDto> gastos) {
        this.gastos = gastos;
    }

    public List<RendaDto> getRendas() {
        return rendas;
    }

    public void setRendas(List<RendaDto> rendas) {
        this.rendas = rendas;
    }

    public static List<DetalhesCategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(DetalhesCategoriaDto::new).collect(Collectors.toList());
    }

}
