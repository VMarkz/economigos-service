package br.com.economigos.service.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    @OneToMany
    private List<Gasto> gastos;
    @OneToMany
    private List<Renda> rendas;

    public Categoria(String categoria) {
        this.categoria = categoria;
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

    public void setCategoria(String categoria)  {
        this.categoria = categoria;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public List<Renda> getRendas() {
        return rendas;
    }

    public void setRendas(List<Renda> rendas) {
        this.rendas = rendas;
    }

    @Override
    public void update(Observable o, Object arg) {
        String acao = String.valueOf(arg);
        System.out.println(acao);

        if (o.getClass().equals(Gasto.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        } else if (o.getClass().equals(Renda.class)) {
            switch (acao) {
                case "create":
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
        }
    }
}
