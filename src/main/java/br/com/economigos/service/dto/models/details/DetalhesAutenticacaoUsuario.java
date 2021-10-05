package br.com.economigos.service.dto.models.details;

import br.com.economigos.service.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesAutenticacaoUsuario implements UserDetails {

    private String email;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public DetalhesAutenticacaoUsuario(Usuario usuario) {
        System.out.println(usuario.getRoles());
        this.email = usuario.getEmail();
        this.password = usuario.getSenha();
        this.authorities = Arrays.stream(usuario.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public DetalhesAutenticacaoUsuario() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
