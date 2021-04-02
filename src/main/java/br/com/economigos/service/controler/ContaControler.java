package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.ContaDto;
import br.com.economigos.service.controler.dto.DetalhesContaDto;
import br.com.economigos.service.controler.form.ContaForm;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.repository.ContaRepository;
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
@RequestMapping("/economigos/contas")
public class ContaControler {

    @Autowired
    ContaRepository contaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<ContaDto> listar(){
        List<Conta> contas = contaRepository.findAll();
        return ContaDto.converter(contas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
        Conta conta = form.converter(usuarioRepository);
        contaRepository.save(conta);

        URI uri = uriBuilder.path("economigos/contas/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesContaDto> detalhar(@PathVariable Long id){
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent()){
            return ResponseEntity.ok().body(new DetalhesContaDto(conta.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ContaDto> alterar(@PathVariable Long id, @RequestBody @Valid ContaForm form){
        Optional<Conta> optional = contaRepository.findById(id);
        if (optional.isPresent()) {
            Conta usuario = form.atualizar(id, contaRepository);
            return ResponseEntity.ok(new ContaDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent()){
            contaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
