package br.com.economigos.service.controller;

import br.com.economigos.service.dto.PorcentagemCategoriaDto;
import br.com.economigos.service.dto.models.CategoriaDto;
import br.com.economigos.service.dto.models.details.DetalhesCategoriaDto;
import br.com.economigos.service.form.CategoriaForm;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.repository.CategoriaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import br.com.economigos.service.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/economigos/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    @Transactional
    public List<CategoriaDto> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("/porcentagem-gastos")
    @Transactional
    public ResponseEntity<List<PorcentagemCategoriaDto>> listarPorcentagemCategoriaGasto(@RequestHeader("Authorization") String jwt,
                                                                                         @RequestParam(required = false) String tipo) {
        List<Categoria> categorias = new ArrayList<Categoria>();

        if (tipo != null){
            categorias = categoriaRepository.findAllByTipo(tipo);
        }else{
            categorias = categoriaRepository.findAll();
        }

        String email = jwtUtil.extractUsername(jwt.substring(7));
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        Long idUsuario = optionalUsuario.get().getId();

        if (optionalUsuario.isPresent()) {
            Double valorTotal = 0.0;

            List<PorcentagemCategoriaDto> categoriaSomada = new ArrayList<>();

            for (int i = 0; i < categorias.size(); i++) {
                String tempCategoria = categorias.get(i).getCategoria();
                Double tempSoma = 0.0;

                for (Gasto gasto : categorias.get(i).getGastos()) {
                    if (gasto.getConta().getUsuario().getId().equals(idUsuario)
                            || gasto.getCartao().getUsuario().getId().equals(idUsuario)) {
                        tempSoma += gasto.getValor();

                    }
                }

                valorTotal += tempSoma;
                categoriaSomada.add(new PorcentagemCategoriaDto(tempCategoria, tempSoma, 0.0));

            }

            for (PorcentagemCategoriaDto categoriaSoma : categoriaSomada) {
                if (!categoriaSoma.getSoma().equals(0.0)) {
                    categoriaSoma.setPorcentagem(categoriaSoma.getSoma() * 100.0 / valorTotal);
                } else {
                    categoriaSoma.setPorcentagem(0.0);
                }
            }

            return ResponseEntity.ok().body(categoriaSomada.stream().sorted(Comparator.comparingDouble(
                    PorcentagemCategoriaDto::getPorcentagem).reversed()).collect(Collectors.toList()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid CategoriaForm form,
                                               UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesCategoriaDto> detalhar(@PathVariable Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = categoriaRepository.getOne(id);
            return ResponseEntity.ok().body(new DetalhesCategoriaDto(categoria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria")
    @Transactional
    public ResponseEntity<DetalhesCategoriaDto> detalharPorNome(@RequestParam String categoriaNome) {
        Optional<Categoria> optional = categoriaRepository.findByCategoria(categoriaNome);
        if (optional.isPresent()) {
            Categoria categoria = categoriaRepository.getOne(optional.get().getId());
            return ResponseEntity.ok().body(new DetalhesCategoriaDto(categoria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gastos")
    @Transactional
    public List<CategoriaDto> categoriaGasto() {
        List<Categoria> categorias = categoriaRepository.findAll();
        String tipo = "G";
        return CategoriaDto.converter(categorias.stream().filter(categoria -> categoria.getTipo().equals(tipo)).collect(Collectors.toList()));
    }

    @GetMapping("/receitas")
    @Transactional
    public List<CategoriaDto> categoriaReceita() {
        List<Categoria> categorias = categoriaRepository.findAll();
        String tipo = "R";
        return CategoriaDto.converter(categorias.stream().filter(categoria -> categoria.getTipo().equals(tipo)).collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Categoria> alterar(@PathVariable Long id,
                                             @RequestBody @Valid CategoriaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
