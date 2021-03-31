package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.controler.dto.ContaDto;
import br.com.vitor.usercrud.controler.dto.DetalhesUsuarioDto;
import br.com.vitor.usercrud.controler.dto.UsuarioDto;
import br.com.vitor.usercrud.controler.form.ContaForm;
import br.com.vitor.usercrud.controler.form.UsuarioForm;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Usuario;
import br.com.vitor.usercrud.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/economigos/contas")
public class ContaControler {

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public List<ContaDto> listar(){
        List<Conta> contas = contaRepository.findAll();
        return ContaDto.converter(contas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
        Conta conta = form.converter();
        contaRepository.save(conta);

        URI uri = uriBuilder.path("economigos/contas/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDto> detalhar(@PathVariable Long id){
        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isPresent()){
            return ResponseEntity.ok().body(new ContaDto(conta.get()));
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
