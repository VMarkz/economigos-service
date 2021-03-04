package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
//    @OneToMany
//    private List<Gasto> gastos;
//    @OneToMany
//    private List<Renda> rendas;

    public Categoria(String categoria) {
        this.categoria = categoria;
//        this.gastos = gastos;
//        this.rendas = rendas;
    }

    public Categoria() {
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

//    public List<Gasto> getGastos() {
//        return gastos;
//    }
//
//    public void setGastos(List<Gasto> gastos) {
//        this.gastos = gastos;
//    }
//
//    public List<Renda> getRendas() {
//        return rendas;
//    }
//
//    public void setRendas(List<Renda> rendas) {
//        this.rendas = rendas;
//    }
}
