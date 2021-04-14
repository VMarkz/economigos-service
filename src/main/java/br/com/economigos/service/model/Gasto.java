package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@DiscriminatorValue(value = "GASTO")
public class Gasto extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean pago;
    @ManyToOne
    private Cartao cartao;

    public Gasto(Conta conta, Categoria categoria,Double valor, String descricao, Boolean fixo, Boolean pago, LocalDateTime dataPagamento) {
        super(conta, categoria, valor, descricao, fixo, dataPagamento, "Gasto");
        this.pago = pago;
    }

    public Gasto(Cartao cartao,  Categoria categoria,Double valor, String descricao, Boolean fixo, Boolean pago, LocalDateTime dataPagamento, String a) {
        super(categoria, valor, descricao, fixo, dataPagamento, "Gasto");
        this.pago = pago;
        this.cartao = cartao;
    }

    public Gasto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
