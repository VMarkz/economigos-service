package br.com.vitor.usercrud.controler.form;

import br.com.vitor.usercrud.model.Categoria;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Renda;
import br.com.vitor.usercrud.repository.RendaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RendaForm implements CommonForm{

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

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getFixo() {
        return fixo;
    }

    public void setFixo(Boolean fixo) {
        this.fixo = fixo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Renda converter() {
        return new Renda(this.valor, this.descricao, this.fixo, this.categoria, this.conta);
    }

    public Renda atualizar(Long id, RendaRepository rendaRepository) {
        Renda renda = rendaRepository.getOne(id);

        renda.setConta(this.conta);
        renda.setCategoria(this.categoria);
        renda.setDescricao(this.descricao);
        renda.setFixo(this.fixo);
        renda.setValor(this.valor);
        renda.setDataPagamento(this.dataPagamento);

        return renda;
    }
}
