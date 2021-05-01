package br.com.economigos.service.model;

import br.com.economigos.service.repository.RendaRepository;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Renda extends Contabil{

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

    public static Double getUltimosMeses (String anoMes, RendaRepository rendaRepository, Long idConta){
        Double soma = 0.0;

        List<Renda> rendasMes = rendaRepository.findByDataPagamentoIsStartingWithByConta(anoMes, idConta);
        for (Renda renda : rendasMes) {
            soma += renda.getValor(); }
        return soma;
    }
}