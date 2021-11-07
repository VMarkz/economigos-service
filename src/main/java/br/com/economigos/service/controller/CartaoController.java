package br.com.economigos.service.controller;

import br.com.economigos.service.controler.form.PagarCartaoForm;
import br.com.economigos.service.model.Cartao;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.CartaoRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import br.com.economigos.service.dto.ContabilUltimasAtividadesDto;
import br.com.economigos.service.dto.UltimasAtividadesDto;
import br.com.economigos.service.dto.models.CartaoDto;
import br.com.economigos.service.dto.models.details.DetalhesCartaoDto;
import br.com.economigos.service.form.CartaoForm;
import br.com.economigos.service.repository.*;
import br.com.economigos.service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@CrossOrigin
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
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    @Transactional
    public ResponseEntity<List<CartaoDto>> listar(@RequestHeader("Authorization") String jwt) {
        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            List<Cartao> cartoes = cartaoRepository.findAllByUsuario(optionalUsuario.get().getId());
            return ResponseEntity.ok().body(CartaoDto.converter(cartoes));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartaoDto> cadastrar(@RequestBody @Valid CartaoForm form,
                                               UriComponentsBuilder uriBuilder,
                                               @RequestHeader("Authorization") String jwt) {
        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        Cartao cartao = form.converter(optionalUsuario.get());
        cartaoRepository.save(cartao);

        URI uri = uriBuilder.path("economigos/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        return ResponseEntity.created(uri).body(new CartaoDto(cartao));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCartaoDto> detalhar(@PathVariable Long id){
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if(cartaoOptional.isPresent()){
            Cartao cartao = cartaoRepository.getOne(id);
            return ResponseEntity.ok().body(new DetalhesCartaoDto(cartao.setValorFatura(cartao,gastoRepository)));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idCartao}/ultimas-atividades")
    public ResponseEntity<UltimasAtividadesDto> ultimasAtividades(@RequestHeader("Authorization") String jwt,
                                                                  @PathVariable Long idCartao) {
        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        Optional<Cartao> optionalCartao = cartaoRepository.findCartaoByUsuario(idCartao, optionalUsuario.get().getId());
        if (optionalCartao.isPresent()) {
            Cartao cartao = cartaoRepository.getOne(idCartao);

            List<Gasto> gastos = gastoRepository.findGastoByCartao(cartao.getId());
            List<ContabilUltimasAtividadesDto> ultimasAtividadesDtos = new ArrayList<>();

            for (Gasto gasto : gastos) {

                ultimasAtividadesDtos.add(new ContabilUltimasAtividadesDto(gasto.getDescricao(),
                        gasto.getDataPagamento(), gasto.getValor(), gasto.getTipo(), gasto.getCategoria().getCategoria(),
                        gasto.getCartao().getNome()));
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

    @PutMapping("/pagar")
    @Transactional
    public ResponseEntity<?> pagarFatura(@RequestBody @Valid PagarCartaoForm form){
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(form.getIdCartao());
        Optional<Conta> contaOptional = contaRepository.findById(form.getIdConta());
        if (cartaoOptional.isPresent() && contaOptional.isPresent()){
            Cartao cartao = cartaoRepository.getOne(form.getIdCartao());
            Conta conta = contaRepository.getOne(form.getIdConta());
            cartao.setValorFatura(cartao,gastoRepository);
            conta.setValorAtual(conta.getValorAtual()-cartao.getValor());
            cartao.setLimite(cartao.getLimite()+ form.getValor());
            cartao.setValor(cartao.getValor()-form.getValor());
            cartao.setPago(true);
            return null;
        }
        return ResponseEntity.badRequest().build();
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
