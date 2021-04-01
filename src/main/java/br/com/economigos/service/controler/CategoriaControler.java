package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.CategoriaDto;
import br.com.economigos.service.controler.dto.DetalhesCategoriaDto;
import br.com.economigos.service.controler.dto.GastoDto;
import br.com.economigos.service.controler.form.CategoriaForm;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/economigos/categorias")
public class CategoriaControler {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaDto> listar(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    @GetMapping("/porcentagem-gastos")
    public List<?> listarPorcentagemCategoriaGasto(){
        List<Categoria> categorias = categoriaRepository.findAll();
        List<DetalhesCategoriaDto> listaConvertida = DetalhesCategoriaDto.converter(categorias);
        List<List<GastoDto>> listaGastos = new ArrayList<>();

        Double valorTotal = 0.0;

        List<HashMap<String, Double>> categoriaSomas = new ArrayList<>();

        for(int i=0; i<categorias.size(); i++){
            HashMap<String, Double> tempHashmap = new HashMap<>();
            String tempCategoria = "";
            Double tempSoma = 0.0;
            for (DetalhesCategoriaDto detalhesCategoriaDto : listaConvertida) {
                for (GastoDto gasto : detalhesCategoriaDto.getGastos()) {
                    valorTotal += gasto.getValor();
                    tempCategoria = gasto.getCategoria();
                    tempSoma += gasto.getValor();
                }
            }
            tempHashmap.put(tempCategoria, tempSoma);
            categoriaSomas.add(tempHashmap);
        }
        System.out.println(categoriaSomas);
        return null;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
        Categoria categoria = form.converter();
        categoriaRepository.save(categoria);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesCategoriaDto> detalhar(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return ResponseEntity.ok().body(new DetalhesCategoriaDto(categoria.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Categoria> alterar(@PathVariable Long id, @RequestBody @Valid CategoriaForm form){
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
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()){
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
