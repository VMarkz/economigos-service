package br.com.economigos.service.form;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.repository.CategoriaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaForm {

    @NotNull
    @NotEmpty
    private String categoria, tipo;


    public CategoriaForm() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Categoria converter() {
        return new Categoria(this.categoria, this.tipo);
    }

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getOne(id);

        categoria.setCategoria(this.categoria);
        categoria.setTipo(this.categoria);

        return categoria;
    }
}
