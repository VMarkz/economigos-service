package br.com.economigos.service.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "R")
public class Renda extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Renda(Double valor, String descricao, Boolean fixo, Categoria categoria, Conta conta) {
        super(valor, descricao, fixo, categoria);
    }

    public Renda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}