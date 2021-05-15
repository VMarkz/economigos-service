package br.com.economigos.service.controller;

import br.com.economigos.service.dto.models.ContaDto;
import br.com.economigos.service.dto.models.RendaDto;
import br.com.economigos.service.dto.models.details.DetalhesRendaDto;
import br.com.economigos.service.form.RendaForm;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.RendaRepository;
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
@RequestMapping("/economigos/rendas")
public class RendaController {

    @Autowired
    private RendaRepository rendaRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<RendaDto> listar() {
        List<Renda> rendas = rendaRepository.findAll();
        return RendaDto.converter(rendas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RendaDto> cadastrar(@RequestBody @Valid RendaForm form,
                                              UriComponentsBuilder uriBuilder) {
        Renda renda = form.converter(contaRepository, categoriaRepository);

        rendaRepository.save(renda);
        renda.addObserver(new Conta());
        renda.addObserver(new Categoria());
        renda.notificaObservador("create");

        if (renda.getRecebido()) {
            renda.setRecebido(false);
            receberRenda(renda.getId());
        }

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(renda.getId()).toUri();
        return ResponseEntity.created(uri).body(new RendaDto(renda));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesRendaDto> detalhar(@PathVariable Long id) {
        Optional<Renda> renda = rendaRepository.findById(id);

        if (renda.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesRendaDto(renda.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RendaDto> alterar(@PathVariable Long id,
                                            @RequestBody @Valid RendaForm form) {
        Optional<Renda> optional = rendaRepository.findById(id);

        if (optional.isPresent()) {
            Renda renda = form.atualizar(id, rendaRepository);

            renda.addObserver(new Conta());
            renda.addObserver(new Categoria());
            renda.notificaObservador("update");

            return ResponseEntity.ok(new RendaDto(renda));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Renda> optional = rendaRepository.findById(id);

        if (optional.isPresent()) {
            Renda renda = rendaRepository.getOne(id);

            renda.addObserver(new Conta());
            renda.addObserver(new Categoria());
            rendaRepository.deleteById(id);
            renda.notificaObservador("delete");

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/receber/{id}")
    @Transactional
    public ResponseEntity<?> receberRenda(@PathVariable Long id) {
        Optional<Renda> optionalRenda = rendaRepository.findById(id);
        Optional<Conta> optionalConta = contaRepository.findById(optionalRenda.get().getConta().getId());

        if (optionalRenda.isPresent() && optionalConta.isPresent()) {
            Renda renda = rendaRepository.getOne(id);
            Conta conta = contaRepository.getOne(renda.getConta().getId());

            if (!renda.getRecebido()) {
                renda.setRecebido(true);
                conta.setValorAtual((conta.getValorAtual() + renda.getValor()));

                return ResponseEntity.ok().body(new ContaDto(conta));
            } else {
                return ResponseEntity.badRequest().body("Renda já recebida");
            }
        }
        return ResponseEntity.notFound().build();
    }

}