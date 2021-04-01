package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.DetalhesGastoDto;
import br.com.economigos.service.controler.dto.GastoDto;
import br.com.economigos.service.controler.form.GastoForm;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/gastos")
public class GastoControler {

    @Autowired
    private GastoRepository gastoRepository;

    GastoDto dto;

    @GetMapping
    public List<GastoDto> listar(){
        List<Gasto> gastos = gastoRepository.findAll();
        return GastoDto.converter(gastos);
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
    public ResponseEntity<DetalhesGastoDto> detalhar(@PathVariable Long id) {
        Optional<Gasto> gasto = gastoRepository.findById(id);
        if (gasto.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesGastoDto(gasto.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GastoDto> alterar(@PathVariable Long id, @RequestBody @Valid GastoForm form) {
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = form.atualizar(id, gastoRepository);
            return ResponseEntity.ok(new GastoDto(gasto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pagar/{id}")
    @Transactional
    public ResponseEntity<List<GastoDto>> pagar(@PathVariable Long id) {
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = gastoRepository.getOne(id);
            gasto.setPago(true);
            List<Gasto> gastos = new ArrayList<>();
            gastos.add(gasto);
            return ResponseEntity.ok().body(dto.converter(gastos));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cancelar-pagamento/{id}")
    @Transactional
    public ResponseEntity<List<GastoDto>> cancelarPagamento(@PathVariable Long id) {
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = gastoRepository.getOne(id);
            gasto.setPago(false);
            List<Gasto> gastos = new ArrayList<>();
            gastos.add(gasto);
            return ResponseEntity.ok().body(dto.converter(gastos));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Gasto> gasto = gastoRepository.findById(id);
        if (gasto.isPresent()) {
            gastoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
