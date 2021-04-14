package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class DetalhesUsuarioDto {

    private Long id;
    private String email;
    private LocalDateTime dataCriacao;
    private List<ContaDto> contaDtos;
    private List<MetaDto> metaDtos;

    public DetalhesUsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.dataCriacao = usuario.getDataCriacao();
        this.contaDtos = ContaDto.converter(usuario.getContas());
        this.metaDtos = MetaDto.converter(usuario.getMetas());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ContaDto> getContaDtos() {
        return contaDtos;
    }

    public void setContaDtos(List<ContaDto> contaDtos) {
        this.contaDtos = contaDtos;
    }

    public List<MetaDto> getMetaDtos() {
        return metaDtos;
    }

    public void setMetaDtos(List<MetaDto> metaDtos) {
        this.metaDtos = metaDtos;
    }
}
