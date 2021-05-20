package br.com.economigos.service.controller;

import br.com.economigos.service.dto.ContabilUltimasAtividadesDto;
import br.com.economigos.service.dto.UltimasAtividadesDto;
import br.com.economigos.service.dto.models.CartaoDto;
import br.com.economigos.service.dto.models.details.DetalhesCartaoDto;
import br.com.economigos.service.form.CartaoForm;
import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/economigos/cartoes")
public class CartaoController {

    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    RendaRepository rendaRepository;
    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    @Transactional
    public List<CartaoDto> listar() {
        List<Cartao> cartoes = cartaoRepository.findAll();
        return CartaoDto.converter(cartoes);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Transactional
    public ResponseEntity<List<CartaoDto>> listar(@PathVariable Long idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

        if (optional.isPresent()) {
            List<Cartao> cartoes = cartaoRepository.findAllByUsuario(idUsuario);
            return ResponseEntity.ok().body(CartaoDto.converter(cartoes));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartaoDto> cadastrar(@RequestBody @Valid CartaoForm form,
                                               UriComponentsBuilder uriBuilder) {
        Cartao cartao = form.converter(usuarioRepository);
        cartaoRepository.save(cartao);

        URI uri = uriBuilder.path("economigos/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDto(cartao));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCartaoDto> detalhar(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao.isPresent()) {
            return ResponseEntity.ok().body(new DetalhesCartaoDto(cartao.get()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{idCartao}/usuario/{idUsuario}/ultimas-atividades")
    public ResponseEntity<UltimasAtividadesDto> ultimasAtividades(@PathVariable Long idUsuario,
                                                                  @PathVariable Long idCartao) {
        Optional<Cartao> optionalCartao = cartaoRepository.findCartaoByUsuario(idCartao, idUsuario);
        if (optionalCartao.isPresent()) {
            Cartao cartao = cartaoRepository.getOne(idCartao);

            List<Gasto> gastos = gastoRepository.findGastoByCartao(cartao.getId());
            List<ContabilUltimasAtividadesDto> ultimasAtividadesDtos = new ArrayList<>();

            for (Gasto gasto : gastos) {
                ultimasAtividadesDtos.add(new ContabilUltimasAtividadesDto(gasto.getDescricao(),
                        gasto.getDataPagamento(), gasto.getValor(), gasto.getTipo()));
            }

            Collections.sort(ultimasAtividadesDtos);

            return ResponseEntity.ok().body(new UltimasAtividadesDto("cartao", cartao.getId(), ultimasAtividadesDtos));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CartaoDto> alterar(@PathVariable Long id,
                                             @RequestBody @Valid CartaoForm form) {
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
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isPresent()) {
            cartaoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
