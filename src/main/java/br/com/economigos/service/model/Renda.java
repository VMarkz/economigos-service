package br.com.economigos.service.model;

import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;

import javax.persistence.*;
import java.util.List;

@Entity
public class Renda extends Contabil{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean recebido;

    public Renda(Conta conta, Categoria categoria, Double valor, String descricao, Boolean fixo, Boolean recebido, String dataPagamento) {
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

    public static Double doSomething (String anoMes, RendaRepository rendaRepository, Long idConta){
        Double soma = 0.0;

        List<Renda> rendasMes = rendaRepository.findByDataPagamentoIsStartingWith(anoMes, idConta);
        for (Renda renda : rendasMes) {
            soma += renda.getValor(); }
        return soma;
    }
}