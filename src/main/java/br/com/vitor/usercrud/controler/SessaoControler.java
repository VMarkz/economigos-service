package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.controler.dto.UsuarioDto;
import br.com.vitor.usercrud.controler.form.UsuarioForm;
import br.com.vitor.usercrud.model.Usuario;
import br.com.vitor.usercrud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sessao")
public class SessaoControler {

    private Sessao sessao;
    private List<Usuario> usuarios;
    @Autowired
    private UsuarioRepository usuarioRepository;

/////CRIAR ENDPOINT PARA RETORNAR TODOS OS LOGADOS ATRUALMENTE

    @GetMapping("/login")
    public ResponseEntity<?> logar(@RequestBody @Valid UsuarioForm form){
        usuarios = usuarioRepository.findByLogin(form.getEmail(), form.getSenha());

        if(!usuarios.isEmpty()){
            sessao = new Sessao((UsuarioDto.converter(usuarios)).get(0));
            return ResponseEntity.ok(sessao);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {

        if(sessao.getStatus()){
            sessao.logout();
            return ResponseEntity.ok(sessao);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
