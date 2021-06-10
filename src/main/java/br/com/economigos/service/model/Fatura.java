package br.com.economigos.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Fatura extends Gasto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private LocalDate vencimento;
    private Cartao cartao;

    public Fatura(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, Boolean pago, String dataPagamento, Long id, Double valor1, LocalDate vencimento, Boolean pago1) {
        super(conta, categoria, valor, descricao, fixo, pago, dataPagamento);
        this.id = id;
        this.valor = valor1;
        this.vencimento = vencimento;
    }

    public Fatura(){

    }

}
