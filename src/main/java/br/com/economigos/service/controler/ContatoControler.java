package br.com.economigos.service.controler;

import br.com.economigos.service.controler.form.ContatoForm;
import br.com.economigos.service.model.Contato;
import br.com.economigos.service.repository.ContatoRepository;
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
@RequestMapping("/economigos/contatos")
public class ContatoControler {

    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping
    @Transactional
    public List<Contato> listar(){
        List<Contato> contatos = contatoRepository.findAll();
        return contatos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Contato> cadastrar(@RequestBody @Valid ContatoForm form, UriComponentsBuilder uriBuilder){
        Contato contato = form.converter();
        contatoRepository.save(contato);

        URI uri = uriBuilder.path("/contatos/{id}").buildAndExpand(contato.getId()).toUri();
        return ResponseEntity.created(uri).body(contato);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Contato> detalhar(@PathVariable Long id){
        Optional<Contato> contato = contatoRepository.findById(id);

        if(contato.isPresent()){
            return ResponseEntity.ok().body(contato.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
