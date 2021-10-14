package br.com.economigos.service.controller;

import br.com.economigos.service.dto.models.MetaDto;
import br.com.economigos.service.form.MetaForm;
import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.MetaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import br.com.economigos.service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/economigos/metas")
public class MetaController {

    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    @Transactional
    public ResponseEntity<List<MetaDto>> listar(@RequestHeader("Authorization") String jwt) {

        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (optionalUsuario.isPresent()) {
            List<Meta> metas = metaRepository.findAllByUsuario(optionalUsuario.get().getId());
            return ResponseEntity.ok().body(MetaDto.converter(metas));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MetaDto> cadastrar(@RequestBody @Valid MetaForm form,
                                             UriComponentsBuilder uriBuilder) {
        Meta meta = form.converter(usuarioRepository);

        metaRepository.save(meta);
        meta.addObserver(new Usuario());
        meta.notificaObservador("create");

        URI uri = uriBuilder.path("/metas/{id}").buildAndExpand(meta.getId()).toUri();
        return ResponseEntity.created(uri).body(new MetaDto(meta));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<MetaDto> detalhar(@PathVariable Long id) {
        Optional<Meta> optionalMeta = metaRepository.findById(id);

        if (optionalMeta.isPresent()) {
            Meta meta = metaRepository.getOne(id);
            return ResponseEntity.ok().body(new MetaDto(meta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MetaDto> alterar(@PathVariable Long id,
                                           @RequestBody @Valid MetaForm form) {
        Optional<Meta> optional = metaRepository.findById(id);

        if (optional.isPresent()) {
            Meta meta = form.atualizar(id, metaRepository);

            meta.addObserver(new Usuario());
            meta.notificaObservador("update");

            return ResponseEntity.ok(new MetaDto(meta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<MetaDto> atualizarValor(@PathVariable Long id,
                                                  @RequestParam Double valor,
                                                  @RequestParam Boolean acrescentando) {
        Optional<Meta> optionalMeta = metaRepository.findById(id);

        if (optionalMeta.isPresent()) {
            Meta meta = metaRepository.getOne(id);
            if (meta.getAtiva() && !meta.getFinalizada()) {
                if (acrescentando) {
                    meta.setValorAtual(meta.getValorAtual() + valor);
                    return ResponseEntity.ok().body(new MetaDto(meta));
                } else {
                    meta.setValorAtual(meta.getValorAtual() - valor);
                    return ResponseEntity.ok().body(new MetaDto(meta));
                }
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/desativar-ativar")
    @Transactional
    public ResponseEntity<MetaDto> destivarAtivar(@PathVariable Long id) {
        Optional<Meta> optionalMeta = metaRepository.findById(id);

        if (optionalMeta.isPresent()) {
            Meta meta = metaRepository.getOne(id);
            if (meta.getAtiva()) {
                meta.setAtiva(false);
                return ResponseEntity.ok().body(new MetaDto(meta));
            } else {
                meta.setAtiva(true);
                return ResponseEntity.ok().body(new MetaDto(meta));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/finalizar")
    @Transactional
    public ResponseEntity<MetaDto> finalizar(@PathVariable Long id) {
        Optional<Meta> optionalMeta = metaRepository.findById(id);

        if (optionalMeta.isPresent()) {
            Meta meta = metaRepository.getOne(id);

            if (!meta.getFinalizada()) {
                meta.setFinalizada(true);
            } else {
                meta.setFinalizada(false);
            }
            return ResponseEntity.ok().body(new MetaDto(meta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Meta> optional = metaRepository.findById(id);

        if (optional.isPresent()) {
            Meta meta = metaRepository.getOne(id);

            meta.addObserver(new Usuario());
            metaRepository.deleteById(id);
            meta.notificaObservador("delete");

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
