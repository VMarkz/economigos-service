package br.com.economigos.service.form;

import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.MetaRepository;
import br.com.economigos.service.repository.UsuarioRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MetaForm {

    private String nome;
    private String descricao;
    @NotNull
    private Boolean metaGasto;
    private Double valorAtual;
    @NotNull
    private Double valorFinal;
    private LocalDate dataFinal;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getMetaGasto() {
        return metaGasto;
    }

    public void setMetaGasto(Boolean metaGasto) {
        this.metaGasto = metaGasto;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Meta converter(Usuario usuario) {
        return new Meta(this.nome, this.descricao, this.metaGasto, this.valorAtual, this.valorFinal,
                this.dataFinal, usuario);
    }

    public Meta atualizar(Long id, MetaRepository metaRepository) {
        Meta meta = metaRepository.getOne(id);

        meta.setNome(this.nome);
        meta.setDescricao(this.descricao);
        meta.setMetaGasto(this.metaGasto);
        meta.setValorAtual(this.valorAtual);
        meta.setValorFinal(this.valorFinal);

        return meta;
    }

}
