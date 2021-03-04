package br.com.vitor.usercrud.controler.dto;

import br.com.vitor.usercrud.model.Usuario;

import java.time.LocalDateTime;

public class DetalhesUsuarioDto {

    private Long id;
    private String email;
    private String usuario;
    private LocalDateTime dataCriacao;

    public DetalhesUsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.usuario = usuario.getUsuario();
        this.dataCriacao = usuario.getDataCriacao();
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

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
