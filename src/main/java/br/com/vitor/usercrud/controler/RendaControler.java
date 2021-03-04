package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.controler.dto.DetalhesRendaDto;
import br.com.vitor.usercrud.controler.dto.RendaDto;
import br.com.vitor.usercrud.controler.form.RendaForm;
import br.com.vitor.usercrud.model.Renda;
import br.com.vitor.usercrud.repository.RendaRepository;
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
@RequestMapping("/rendas")
public class RendaControler {

    @Autowired
    private RendaRepository rendaRepository;

    @GetMapping
    public List<Renda> listar(){
        List<Renda> rendas = rendaRepository.findAll();
        return rendas;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Renda> cadastrar(@RequestBody @Valid RendaForm form, UriComponentsBuilder uriBuilder) {
        Renda renda = form.converter();
        rendaRepository.save(renda);

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(renda.getId()).toUri();
        return ResponseEntity.created(uri).body(renda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesRendaDto> detalhar(@PathVariable Long id){
        Optional<Renda> renda = rendaRepository.findById(id);
        if(renda.isPresent()){
            return ResponseEntity.ok().body(new DetalhesRendaDto(renda.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RendaDto> alterar(@PathVariable Long id, @RequestBody @Valid RendaForm form){
        Optional<Renda> optional = rendaRepository.findById(id);
        if (optional.isPresent()) {
            Renda renda = form.atualizar(id, rendaRepository);
            return ResponseEntity.ok(new RendaDto(renda));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Renda> renda = rendaRepository.findById(id);
        if(renda.isPresent()){
            rendaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
