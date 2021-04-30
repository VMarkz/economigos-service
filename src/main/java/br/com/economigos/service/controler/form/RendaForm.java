package br.com.economigos.service.controler.form;

import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.RendaRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RendaForm implements CommonForm {

    private Long idConta;
    @NotNull
    private Long idCategoria;
    @NotNull
    private Double valor;
    @NotNull
    private Boolean recebido;
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

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getRecebido() {
        return recebido;
    }

    public void setRecebido(Boolean recebido) {
        this.recebido = recebido;
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

    public Renda converter(ContaRepository contaRepository, CategoriaRepository categoriaRepository) {
        Conta conta = contaRepository.getOne(this.idConta);
        Categoria categoria = categoriaRepository.getOne(this.idCategoria);
        return new Renda(conta, categoria, this.valor, this.descricao, this.fixo, this.recebido, this.dataPagamento);
    }

    public Renda atualizar(Long id, RendaRepository rendaRepository) {
        Renda renda = rendaRepository.getOne(id);

        renda.setDescricao(this.descricao);
        renda.setFixo(this.fixo);
        renda.setValor(this.valor);
        renda.setDataPagamento(this.dataPagamento);

        return renda;
    }

}
