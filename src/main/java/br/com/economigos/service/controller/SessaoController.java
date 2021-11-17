package br.com.economigos.service.controller;

import br.com.economigos.service.dto.AuthenticationResponse;
import br.com.economigos.service.dto.models.UsuarioDto;
import br.com.economigos.service.dto.models.details.DetalhesAutenticacaoUsuario;
import br.com.economigos.service.form.UsuarioLoginForm;
import br.com.economigos.service.model.Sessao;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.UsuarioRepository;
import br.com.economigos.service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/economigos/sessao")
public class SessaoController {

    private Sessao sessao;
    private List<Usuario> usuarios;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> logar(@RequestBody @Valid UsuarioLoginForm form) {
        usuarios = usuarioRepository.findByLogin(form.getEmail(), form.getSenha());

        if (!usuarios.isEmpty()) {
            sessao = new Sessao((UsuarioDto.converter(usuarios)).get(0));
            return ResponseEntity.ok(sessao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/logout")
//    @Transactional
//    public ResponseEntity<?> logout() {
//
//        if (sessao.getStatus()) {
//            sessao.logout();
//            return ResponseEntity.ok(sessao);
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid UsuarioLoginForm form) throws Exception{
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(form.getEmail());

        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();

            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha())
                );
            }catch (BadCredentialsException e){
                return ResponseEntity.badRequest().body("Incorrect email or password");
            }

            final UserDetails userDetails = new DetalhesAutenticacaoUsuario(usuario);

            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/verificar")
    @Transactional
    public ResponseEntity<?> verificar(@RequestHeader("Authorization") String jwt) {
        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()){
            if (jwtUtil.validateToken(jwt, new DetalhesAutenticacaoUsuario())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
