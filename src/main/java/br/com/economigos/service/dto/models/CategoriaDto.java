package br.com.economigos.service.dto.models;

import br.com.economigos.service.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private Long id;
    private String categoria;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.categoria = categoria.getCategoria();
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

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

}
