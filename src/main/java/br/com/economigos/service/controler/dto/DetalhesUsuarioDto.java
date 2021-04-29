package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class DetalhesUsuarioDto {

    private Long id;
    private String email;
    private LocalDateTime dataCriacao;
    private Double valorAtual;
    private List<ContaDto> contaDtos;
    private List<MetaDto> metaDtos;
    private List<CartaoDto> cartaoDtos;

    public DetalhesUsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.dataCriacao = usuario.getDataCriacao();
        this.contaDtos = ContaDto.converter(usuario.getContas());
        this.metaDtos = MetaDto.converter(usuario.getMetas());
        this.cartaoDtos = CartaoDto.converter(usuario.getCartoes());
        this.valorAtual = 0.0;
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
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

    public List<CartaoDto> getCartaoDtos() {
        return cartaoDtos;
    }

    public void setCartaoDtos(List<CartaoDto> cartaoDtos) {
        this.cartaoDtos = cartaoDtos;
    }
}
