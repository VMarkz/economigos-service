package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.ContaDto;
import br.com.economigos.service.controler.dto.DetalhesGastoDto;
import br.com.economigos.service.controler.dto.GastoDto;
import br.com.economigos.service.controler.form.GastoForm;
import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.CartaoRepository;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.GastoRepository;
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
@RequestMapping("/economigos/gastos")
public class GastoControler {

    @Autowired
    private GastoRepository gastoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<GastoDto> listar() {
        List<Gasto> gastos = gastoRepository.findAll();
        return GastoDto.converter(gastos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<GastoDto> cadastrar(@RequestBody @Valid GastoForm form, UriComponentsBuilder uriBuilder) {
        Gasto gasto = form.converter(cartaoRepository, contaRepository, categoriaRepository);

        gasto.addObserver(new Conta());
        gasto.addObserver(new Categoria());
        gasto.notificaObservador("create");


        if (form.getGastoCartao()){
            Optional<Cartao> cartaoOptional = cartaoRepository.findById(gasto.getCartao().getId());
            if (cartaoOptional.isPresent()){
                Cartao cartao = cartaoRepository.getOne(gasto.getCartao().getId());
                cartao.setLimite(cartao.getLimite() - gasto.getValor());
            }
            gasto.dividirParcela(gasto, gastoRepository);
            gasto.getValorParcela(gasto);
        } else {
            gastoRepository.save(gasto);
            if (gasto.getPago()) {
                gasto.setPago(false);
                pagarGasto(gasto.getId());
            }
        }

        URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(gasto.getId()).toUri();
        return ResponseEntity.created(uri).body(new GastoDto(gasto));
    }

    @GetMapping("/{id}")
    @Transactional
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
            gasto.addObserver(new Conta());
            gasto.addObserver(new Categoria());
            gasto.notificaObservador("update");
            return ResponseEntity.ok(new GastoDto(gasto));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pagar/{id}")
    @Transactional
    public ResponseEntity<?> pagarGasto(@PathVariable Long id) {
        Optional<Gasto> optionalGasto = gastoRepository.findById(id);
        Optional<Conta> optionalConta = contaRepository.findById(optionalGasto.get().getConta().getId());

        if (optionalGasto.isPresent() && optionalConta.isPresent()) {
            Gasto gasto = gastoRepository.getOne(id);
            Conta conta = contaRepository.getOne(gasto.getConta().getId());
            if (!gasto.getPago()) {
                gasto.setPago(true);
                conta.setValorAtual((conta.getValorAtual() - gasto.getValor()));
                gasto.addObserver(new Conta());
                gasto.addObserver(new Categoria());
                gasto.notificaObservador("update");
                return ResponseEntity.ok().body(new ContaDto(conta));
            } else {
                return ResponseEntity.badRequest().body("Gasto já pago");
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cancelar-pagamento/{id}")
    @Transactional
    public ResponseEntity<GastoDto> cancelarPagamento(@PathVariable Long id) {
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = gastoRepository.getOne(id);
            gasto.setPago(false);
            gasto.addObserver(new Conta());
            gasto.addObserver(new Categoria());
            gasto.notificaObservador("update");
            return ResponseEntity.ok().body(new GastoDto(gasto));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Gasto> optional = gastoRepository.findById(id);
        if (optional.isPresent()) {
            Gasto gasto = gastoRepository.getOne(id);
            gasto.addObserver(new Conta());
            gasto.addObserver(new Categoria());
            gastoRepository.deleteById(id);
            gasto.notificaObservador("delete");
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}