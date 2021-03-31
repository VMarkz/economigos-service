package br.com.vitor.usercrud.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue(value = "G")
public class Gasto extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean pago;

    public Gasto(Double valor, String descricao, LocalDateTime data, Boolean fixo, Categoria categoria) {
        super(valor, descricao, fixo, categoria);
        this.pago = false;
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
