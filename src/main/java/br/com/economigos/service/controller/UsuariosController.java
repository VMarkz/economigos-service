package br.com.economigos.service.controller;

import br.com.economigos.service.dto.ContabilUltimasAtividadesDto;
import br.com.economigos.service.dto.ValorMensalDto;
import br.com.economigos.service.dto.ValorMensalTipoDto;
import br.com.economigos.service.dto.models.UsuarioDto;
import br.com.economigos.service.dto.models.details.DetalhesUsuarioDto;
import br.com.economigos.service.dto.qroFeriasDto;
import br.com.economigos.service.form.ContaForm;
import br.com.economigos.service.form.UsuarioForm;
import br.com.economigos.service.model.*;
import br.com.economigos.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/economigos/usuarios")
public class UsuariosController {

    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    RendaRepository rendaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    CartaoRepository cartaoRepository;

    @GetMapping
    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form,
                                                UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();

        if (!form.verificarCadastro(form.getEmail(), usuarioRepository)) {
            usuarioRepository.save(usuario);

            contaRepository
                    .save(new Conta(usuario, "16", "00000001-01",
                            "Economigos Carteira", "Carteira"));

            URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesUsuarioDto> detalhar(@PathVariable Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()) {
            Usuario usuario = usuarioRepository.getOne(id);
            DetalhesUsuarioDto detalhesUsuarioDto = new DetalhesUsuarioDto(usuario);
            for (Conta conta : usuario.getContas()) {
                detalhesUsuarioDto.setValorAtual(detalhesUsuarioDto.getValorAtual() + conta.getValorAtual());
            }
            return ResponseEntity.ok().body(detalhesUsuarioDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<Long> findByEmail(@RequestParam String email){
        return ResponseEntity.ok().body(usuarioRepository.findByEmail(email).get(0).getId());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> alterar(@PathVariable Long id,
                                              @RequestBody @Valid UsuarioForm form) {
        Optional<Usuario> optional = usuarioRepository.findById(id);

        if (optional.isPresent()) {
            Usuario usuario = form.atualizar(id, usuarioRepository);
            return ResponseEntity.ok(new UsuarioDto(usuario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/ultimos-meses")
    @Transactional
    public ResponseEntity<List<ValorMensalTipoDto>> ultimosMeses(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = usuarioRepository.getOne(id);

            List<ValorMensalDto> valorMensalGastosDtos = new ArrayList<>();
            List<ValorMensalDto> valorMensalRendasDtos = new ArrayList<>();
            List<ValorMensalTipoDto> valorMensalTipoDtos = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                LocalDate localDate = LocalDate.now().minusMonths(i);
                String anoMes = localDate.toString().substring(0, 7);
                String mes = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                for (Conta conta : usuario.getContas()) {
                    valorMensalGastosDtos.add(new ValorMensalDto(mes, Gasto.getUltimosMeses(anoMes, gastoRepository, conta.getId())));
                    valorMensalRendasDtos.add(new ValorMensalDto(mes, Renda.getUltimosMeses(anoMes, rendaRepository, conta.getId())));
                }
            }

            valorMensalTipoDtos.add(new ValorMensalTipoDto("Gasto", valorMensalGastosDtos));
            valorMensalTipoDtos.add(new ValorMensalTipoDto("Renda", valorMensalRendasDtos));

            return ResponseEntity.ok().body(valorMensalTipoDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lancamentos")
    public ResponseEntity<qroFeriasDto> lancamentos(@RequestParam Long idUsuario){

        List<Conta> contas = contaRepository.findAllByUsuario(idUsuario);
        List<Cartao> cartoes = cartaoRepository.findAllByUsuario(idUsuario);
        List<ContabilUltimasAtividadesDto> ultimasAtividadesDtos = new ArrayList<>();

        for (Conta conta : contas) {
            List<Gasto> gastos = gastoRepository.findGastoByConta(conta.getId());
            List<Renda> rendas = rendaRepository.findRendaByConta(conta.getId());
            for (Gasto gasto : gastos) {
                ultimasAtividadesDtos.add(new ContabilUltimasAtividadesDto(
                        gasto.getId(),
                        gasto.getDescricao(),
                        gasto.getDataPagamento(),
                        gasto.getValor(),
                        gasto.getTipo(),
                        gasto.getCategoria().getCategoria(),
                        "Conta"));
            }
            for (Renda renda : rendas) {
                ultimasAtividadesDtos.add(new ContabilUltimasAtividadesDto(
                        renda.getId(),
                        renda.getDescricao(),
                        renda.getDataPagamento(),
                        renda.getValor(),
                        renda.getTipo(),
                        renda.getCategoria().getCategoria(),
                        "Conta"));
            }
        }
        for (Cartao cartao : cartoes) {
            List<Gasto> gastos = gastoRepository.findGastoByCartao(cartao.getId());
            for (Gasto gasto : gastos) {
                ultimasAtividadesDtos.add(new ContabilUltimasAtividadesDto(
                        gasto.getId(),
                        gasto.getDescricao(),
                        gasto.getDataPagamento(),
                        gasto.getValor(),
                        gasto.getTipo(),
                        gasto.getCategoria().getCategoria(),
                        "Cartão"));
            }
        }

        Collections.sort(ultimasAtividadesDtos);

        return ResponseEntity.ok().body(new qroFeriasDto(ultimasAtividadesDtos));
    }

}
