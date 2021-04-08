package br.com.economigos.service.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue(value = "G")
public class Gasto extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean pago;
    @ManyToOne
    private Cartao cartao;

    public Gasto(Double valor, String descricao, LocalDateTime data, Boolean fixo, Categoria categoria, Boolean pago,
                 Cartao cartao) {
        super(valor, descricao, fixo, categoria);
        this.pago = pago;
    }

    public Gasto(Double valor, String descricao, LocalDateTime data, Boolean fixo, Categoria categoria, Boolean pago) {
        super(valor, descricao, fixo, categoria);
        this.pago = pago;
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
}
