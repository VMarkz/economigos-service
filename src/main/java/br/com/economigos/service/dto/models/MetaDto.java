package br.com.economigos.service.dto.models;

import br.com.economigos.service.model.Meta;

import java.util.List;
import java.util.stream.Collectors;

public class MetaDto {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean metaGasto;
    private Double valorInicial;
    private Double valorFinal;

    public MetaDto(Meta meta) {
        this.id = meta.getId();
        this.nome = meta.getNome();
        this.descricao = meta.getDescricao();
        this.metaGasto = meta.getMetaGasto();
        this.valorInicial = meta.getValorInicial();
        this.valorFinal = meta.getValorFinal();
    }

    public static List<MetaDto> converter(List<Meta> metas) {
        return metas.stream().map(MetaDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
