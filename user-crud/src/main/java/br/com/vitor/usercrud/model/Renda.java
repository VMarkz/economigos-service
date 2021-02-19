package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Renda extends Receita{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Conta conta;

    public Renda(Double valor, String descricao, LocalDateTime data, Boolean fixo, Categoria categoria, Conta conta) {
        super(valor, descricao, data, fixo, categoria);
        this.conta = conta;
    }

    public Renda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
