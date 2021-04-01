package br.com.economigos.service.model;

import br.com.economigos.service.controler.dto.UsuarioDto;

import java.time.LocalDateTime;

public class Sessao {

    private UsuarioDto usuario;
    private LocalDateTime inicioSessao;
    private LocalDateTime encerramentoSessao;
    private Boolean status;

    public Sessao(UsuarioDto usuario) {
        this.usuario = usuario;
        this.inicioSessao = LocalDateTime.now();
        this.status = true;
    }

    public UsuarioDto getUsuarios() {
        return usuario;
    }

    public void setUsuarios(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getInicioSessao() {
        return inicioSessao;
    }

    public void setInicioSessao(LocalDateTime inicioSessao) {
        this.inicioSessao = inicioSessao;
    }

    public LocalDateTime getEncerramentoSessao() {
        return encerramentoSessao;
    }

    public void setEncerramentoSessao(LocalDateTime encerramentoSessao) {
        this.encerramentoSessao = encerramentoSessao;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void logout() {
        this.status = false;
        this.encerramentoSessao = LocalDateTime.now();
    }
}
