package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.controler.dto.DetalhesGastoDto;
import br.com.vitor.usercrud.controler.dto.DetalhesRendaDto;
import br.com.vitor.usercrud.controler.dto.GastoDto;
import br.com.vitor.usercrud.controler.dto.RendaDto;
import br.com.vitor.usercrud.controler.form.GastoForm;
import br.com.vitor.usercrud.controler.form.RendaForm;
import br.com.vitor.usercrud.model.Gasto;
import br.com.vitor.usercrud.model.Renda;
import br.com.vitor.usercrud.repository.GastoRepository;
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
@RequestMapping("/gastos")
public class GastoControler {

    @Autowired
    private GastoRepository gastoRepository;

    @GetMapping
    public List<Gasto> listar(){
        List<Gasto> gastos = gastoRepository.findAll();
        return gastos;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Gasto> cadastrar(@RequestBody @Valid GastoForm form, UriComponentsBuilder uriBuilder) {
        Gasto gasto = form.converter();
        gastoRepository.save(gasto);

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(gasto.getId()).toUri();
        return ResponseEntity.created(uri).body(gasto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesGastoDto> detalhar(@PathVariable Long id){
        Optional<Gasto> gasto = gastoRepository.findById(id);
        if(gasto.isPresent()){
            return ResponseEntity.ok().body(new DetalhesGastoDto(gasto.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GastoDto> alterar(@PathVariable Long id, @RequestBody @Valid GastoForm form){
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = form.atualizar(id, gastoRepository);
            return ResponseEntity.ok(new GastoDto(gasto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Gasto> gasto = gastoRepository.findById(id);
        if(gasto.isPresent()){
            gastoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
