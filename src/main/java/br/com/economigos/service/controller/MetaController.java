package br.com.economigos.service.controller;

import br.com.economigos.service.dto.models.MetaDto;
import br.com.economigos.service.form.MetaForm;
import br.com.economigos.service.model.Meta;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.MetaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
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
@RequestMapping("/economigos/metas")
public class MetaController {

    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Transactional
    public List<Meta> listar() {
        List<Meta> metas = metaRepository.findAll();
        return metas;
    }

    @GetMapping("/usuario/{idUsuario}")
    @Transactional
    public ResponseEntity<List<MetaDto>> listar(@PathVariable Long idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

        if (optional.isPresent()) {
            List<Meta> metas = metaRepository.findAllByUsuario(idUsuario);
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

        URI uri = uriBuilder.path("/Metas/{id}").buildAndExpand(meta.getId()).toUri();
        return ResponseEntity.created(uri).body(new MetaDto(meta));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Meta> detalhar(@PathVariable Long id) {
        Optional<Meta> optional = metaRepository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Meta> alterar(@PathVariable Long id,
                                        @RequestBody @Valid MetaForm form) {
        Optional<Meta> optional = metaRepository.findById(id);

        if (optional.isPresent()) {
            Meta meta = form.atualizar(id, metaRepository);

            meta.addObserver(new Usuario());
            meta.notificaObservador("update");

            return ResponseEntity.ok(meta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Meta> atualizarValor(@PathVariable Long id,
                                               @RequestBody Double valor,
                                               @RequestBody Boolean acrecentando) {
        Optional<Meta> optionalMeta = metaRepository.findById(id);

        if (optionalMeta.isPresent()) {
            Meta meta = metaRepository.getOne(id);
            if(acrecentando){
                meta.setValorAtual(meta.getValorAtual() + valor);
                return ResponseEntity.ok().body(meta);
            }else{
                meta.setValorAtual(meta.getValorAtual() - valor);
                return ResponseEntity.ok().body(meta);
            }
        }else{
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
            meta.notificaObservador("create");

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
