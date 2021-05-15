package br.com.economigos.service.form;

import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.MetaRepository;
import br.com.economigos.service.repository.UsuarioRepository;

import javax.validation.constraints.NotNull;

public class MetaForm {

    private Long idUsuario;
    @NotNull
    private String nome;
    private String descricao;
    @NotNull
    private Boolean metaGasto;
    private Double valorInicial;
    @NotNull
    private Double valorFinal;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Meta converter(UsuarioRepository usuarioRepository) {
        Usuario usuario = usuarioRepository.getOne(this.idUsuario);
        return new Meta(usuario, this.nome, this.descricao, this.metaGasto, this.valorInicial, this.valorFinal);
    }

    public Meta atualizar(Long id, MetaRepository metaRepository) {
        Meta meta = metaRepository.getOne(id);

        meta.setNome(this.nome);
        meta.setDescricao(this.descricao);
        meta.setMetaGasto(this.metaGasto);
        meta.setValorInicial(this.valorInicial);
        meta.setValorFinal(this.valorFinal);

        return meta;
    }

}
