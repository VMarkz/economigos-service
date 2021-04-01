package br.com.economigos.service.controler.dto;

import br.com.economigos.service.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto implements CommonDto{

    private Long id;
    private String email;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
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

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

}
