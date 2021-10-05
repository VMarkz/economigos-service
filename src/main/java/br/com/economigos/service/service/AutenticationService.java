package br.com.economigos.service.service;

import br.com.economigos.service.dto.models.details.DetalhesAutenticacaoUsuario;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public DetalhesAutenticacaoUsuario loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        DetalhesAutenticacaoUsuario usuario = new DetalhesAutenticacaoUsuario(optionalUsuario.get());

        System.out.println(usuario.getAuthorities());

        return usuario;
    }
}
