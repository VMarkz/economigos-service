package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@DiscriminatorValue(value = "RENDA")
public class Renda extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean recebido;

    public Renda(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, Boolean recebido, LocalDateTime dataPagamento) {
        super(conta,categoria,valor, descricao, fixo, dataPagamento, "Renda");
        this.recebido = recebido;
    }

    public Renda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRecebido() {
        return recebido;
    }

    public void setRecebido(Boolean recebido) {
        this.recebido = recebido;
    }
}