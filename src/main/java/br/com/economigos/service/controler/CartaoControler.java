package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.CartaoDto;
import br.com.economigos.service.controler.dto.DetalhesCartaoDto;
import br.com.economigos.service.controler.dto.DetalhesUsuarioDto;
import br.com.economigos.service.controler.form.CartaoForm;
import br.com.economigos.service.model.Cartao;
//import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.CartaoRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/cartoes")
public class CartaoControler {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<CartaoDto> listar(){
        List<Cartao> cartoes = cartaoRepository.findAll();
        return CartaoDto.converter(cartoes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartaoDto> cadastrar(@RequestBody @Valid CartaoForm form, UriComponentsBuilder uriBuilder) {
        Cartao cartao = form.converter(usuarioRepository);
        cartaoRepository.save(cartao);

        URI uri = uriBuilder.path("economigos/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDto(cartao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCartaoDto> detalhar(@PathVariable Long id){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isPresent()){
            return ResponseEntity.ok().body(new DetalhesCartaoDto(cartao.get()));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CartaoDto> alterar(@PathVariable Long id, @RequestBody @Valid CartaoForm form){
        Optional<Cartao> optional = cartaoRepository.findById(id);
        if (optional.isPresent()) {
            Cartao cartao = form.atualizar(id, cartaoRepository);
            return ResponseEntity.ok(new CartaoDto(cartao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isPresent()){
            cartaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
