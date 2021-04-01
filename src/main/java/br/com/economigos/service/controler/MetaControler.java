package br.com.economigos.service.controler;

import br.com.economigos.service.controler.form.MetaForm;
import br.com.economigos.service.model.Meta;
import br.com.economigos.service.repository.MetaRepository;
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
@RequestMapping("/economigos/metas")
public class MetaControler {

    @Autowired
    private MetaRepository metaRepository;

    @GetMapping
    public List<Meta> listar(){
        List<Meta> metas = metaRepository.findAll();
        return metas;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Meta> cadastrar(@RequestBody @Valid MetaForm form, UriComponentsBuilder uriBuilder) {
        Meta meta = form.converter();
        metaRepository.save(meta);

        URI uri = uriBuilder.path("/Metas/{id}").buildAndExpand(meta.getId()).toUri();
        return ResponseEntity.created(uri).body(meta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meta> detalhar(@PathVariable Long id){
        Optional<Meta> optional = metaRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Meta> alterar(@PathVariable Long id, @RequestBody @Valid MetaForm form){
        Optional<Meta> optional = metaRepository.findById(id);
        if (optional.isPresent()) {
            Meta meta = form.atualizar(id, metaRepository);
            return ResponseEntity.ok(meta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Meta> optional = metaRepository.findById(id);
        if(optional.isPresent()){
            metaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
