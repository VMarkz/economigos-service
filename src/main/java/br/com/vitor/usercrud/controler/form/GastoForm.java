package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.model.Categoria;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;
import br.com.vitor.usercrud.repository.GastoRepository;
import br.com.vitor.usercrud.repository.RendaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class GastoForm implements CommonForm{

    @NotEmpty
    @NotNull
    private Conta conta;
    @NotEmpty
    @NotNull
    private Double valor;
    @NotEmpty
    @NotNull
    private String descricao;
    @NotEmpty
    @NotNull
    private Boolean fixo;
    @NotEmpty
    @NotNull
    private Categoria categoria;
    @NotEmpty
    @NotNull
    private LocalDateTime dataPagamento;

    @Override
    public Gasto converter() {
        return new Gasto(this.valor,this.descricao,this.dataPagamento,this.fixo,this.categoria);
    }

    public Gasto atualizar(Long id, GastoRepository gastoRepository) {
        Gasto gasto = gastoRepository.getOne(id);

        gasto.setConta(this.conta);
        gasto.setCategoria(this.categoria);
        gasto.setDescricao(this.descricao);
        gasto.setFixo(this.fixo);
        gasto.setValor(this.valor);
        gasto.setDataPagamento(this.dataPagamento);

        return gasto;
    }
}
