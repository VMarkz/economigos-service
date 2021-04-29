package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.DetalhesContaDto;
import br.com.economigos.service.controler.dto.DetalhesUsuarioDto;
import br.com.economigos.service.controler.dto.UsuarioDto;
import br.com.economigos.service.controler.form.UsuarioForm;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/usuarios")
public class UsuariosControler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDto> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        if (!form.verificarCadastro(form.getEmail(), usuarioRepository)){
            usuarioRepository.save(usuario);

            URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
        };
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesUsuarioDto> detalhar(@PathVariable Long id){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            Usuario usuario = usuarioRepository.getOne(id);
            DetalhesUsuarioDto detalhesUsuarioDto = new DetalhesUsuarioDto(usuario);
            for(Conta conta : usuario.getContas()){
                for(Renda renda : conta.getRendas()){
                    detalhesUsuarioDto.setRendaTotal(detalhesUsuarioDto.getRendaTotal()+renda.getValor());
                }
                for(Gasto gasto : conta.getGastos()){
                    detalhesUsuarioDto.setGastoTotal(detalhesUsuarioDto.getGastoTotal()+gasto.getValor());
                }
            }
            return ResponseEntity.ok().body(detalhesUsuarioDto);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form){
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
