package br.com.economigos.service.dto.models;

import java.util.List;

public class AmizadeDto {

    private UsuarioDto usuario;
    private List<UsuarioDto> amigos;

    public AmizadeDto(UsuarioDto usuarioDto, List<UsuarioDto> amigosDto) {
        usuario = usuarioDto;
        amigos = amigosDto;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioDto> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<UsuarioDto> amigos) {
        this.amigos = amigos;
    }
}
