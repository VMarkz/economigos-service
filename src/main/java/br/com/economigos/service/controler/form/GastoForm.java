package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.CartaoRepository;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.GastoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GastoForm {


    @NotNull
    private String conta;
    @NotNull
    private Double valor;
    @NotNull
    private Boolean pago;
    @NotNull
    private String descricao;
    @NotNull
    private Boolean fixo;
    @NotNull
    private String categoria;
    @NotNull
    private LocalDateTime dataPagamento;
    @NotNull
    private Long idCartao;

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Gasto converter(CartaoRepository cartaoRepository, ContaRepository contaRepository, CategoriaRepository categoriaRepository ) {
        Optional<Cartao> cartao = cartaoRepository.findById(this.idCartao);
        Conta conta = contaRepository.findByApelido(this.conta);
        Categoria categoria = categoriaRepository.findByCategoria(this.categoria);
        if (cartao.isPresent()) {
            return new Gasto(cartao.get(),categoria,this.valor,this.descricao,this.pago,this.fixo, this.dataPagamento, "cartão");
        } else {
            return new Gasto(conta, categoria, this.valor, this.descricao, this.fixo, this.pago, this.dataPagamento);
        }
    }

    public Gasto atualizar(Long id, GastoRepository gastoRepository) {
        Gasto gasto = gastoRepository.getOne(id);

        gasto.setDescricao(this.descricao);
        gasto.setFixo(this.fixo);
        gasto.setValor(this.valor);
        gasto.setDataPagamento(this.dataPagamento);

        return gasto;
    }

}
