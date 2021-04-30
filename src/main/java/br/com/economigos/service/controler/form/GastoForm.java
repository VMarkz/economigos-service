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

    private Long idConta;
    private Long idCartao;
    @NotNull
    private Long idCategoria;
    @NotNull
    private Boolean gastoCartao;
    @NotNull
    private Double valor;
    @NotNull
    private Boolean pago;
    @NotNull
    private String descricao;
    @NotNull
    private Boolean fixo;
    @NotNull
    private String dataPagamento;

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Boolean getGastoCartao() {
        return gastoCartao;
    }

    public void setGastoCartao(Boolean gastoCartao) {
        this.gastoCartao = gastoCartao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Gasto converter(CartaoRepository cartaoRepository, ContaRepository contaRepository, CategoriaRepository categoriaRepository) {

        if (gastoCartao) {
            Optional<Cartao> optionalCartao = cartaoRepository.findById(this.idCartao);
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(this.idCategoria);
            if (optionalCartao.isPresent() && optionalCategoria.isPresent()) {
                Cartao cartao = cartaoRepository.getOne(idCartao);
                Categoria categoria = categoriaRepository.getOne(idCategoria);
                return new Gasto(cartao, categoria, this.valor, this.descricao, this.pago, this.fixo, this.dataPagamento, "cartão");
            } else {
                return new Gasto();
            }
        } else {
            Optional<Conta> optionalConta = contaRepository.findById(this.idConta);
            Optional<Categoria> optionalCategoria = categoriaRepository.findById(this.idCategoria);
            if (optionalConta.isPresent() && optionalCategoria.isPresent()) {
                Conta conta = contaRepository.getOne(idConta);
                Categoria categoria = categoriaRepository.getOne(idCategoria);
                return new Gasto(conta, categoria, this.valor, this.descricao, this.fixo, this.pago, this.dataPagamento);
            } else {
                return new Gasto();
            }
        }
    }

    public Gasto atualizar(Long id, GastoRepository gastoRepository) {
        Gasto gasto = gastoRepository.getOne(id);

        gasto.setDescricao(this.descricao);
        gasto.setPago(this.pago);
        gasto.setFixo(this.fixo);
        gasto.setValor(this.valor);
        gasto.setDataPagamento(this.dataPagamento);

        return gasto;
    }

}
