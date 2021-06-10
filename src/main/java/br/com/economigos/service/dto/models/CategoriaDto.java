package br.com.economigos.service.dto.models;

import br.com.economigos.service.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private Long id;
    private String categoria, tipo, path;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.categoria = categoria.getCategoria();
        this.tipo = categoria.getTipo();
        this.path = categoria.getPath();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

}
