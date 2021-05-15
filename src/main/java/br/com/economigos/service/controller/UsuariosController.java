package br.com.economigos.service.controller;

import br.com.economigos.service.dto.*;
import br.com.economigos.service.dto.models.UsuarioDto;
import br.com.economigos.service.dto.models.details.DetalhesUsuarioDto;
import br.com.economigos.service.form.UsuarioForm;
import br.com.economigos.service.model.Conta;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    RendaRepository rendaRepository;

    @GetMapping
    public List<UsuarioDto> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        if (!form.verificarCadastro(form.getEmail(), usuarioRepository)) {
            usuarioRepository.save(usuario);

            URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
        }
        ;
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


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> alterar(@PathVariable Long id, @RequestBody @Valid UsuarioForm form) {
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

            for (int i = 1; i <= 5; i++) {
                LocalDate localDate = LocalDate.now().minusMonths(i);
                String anoMes = localDate.toString().substring(0, 7);
                String mes = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                for (Conta conta : usuario.getContas()) {
                    valorMensalGastosDtos.add(new ValorMensalDto(mes, Gasto.getUltimosMeses(anoMes, gastoRepository, conta.getId())));
                    valorMensalRendasDtos.add(new ValorMensalDto(mes, Renda.getUltimosMeses(anoMes, rendaRepository , conta.getId())));
                }
            }

            valorMensalTipoDtos.add(new ValorMensalTipoDto("Gasto", valorMensalGastosDtos));
            valorMensalTipoDtos.add(new ValorMensalTipoDto("Renda", valorMensalRendasDtos));
            return ResponseEntity.ok().body(valorMensalTipoDtos);
        }
        return ResponseEntity.notFound().build();
    }

}
