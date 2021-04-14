package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.CategoriaDto;
import br.com.economigos.service.controler.dto.DetalhesCategoriaDto;
import br.com.economigos.service.controler.form.CategoriaForm;
import br.com.economigos.service.model.Categoria;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

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
    public HashMap<String, Double> listarPorcentagemCategoriaGasto(){
        List<Categoria> categorias = categoriaRepository.findAll();

        Double valorTotal = 0.0;

        List<HashMap<String, Double>> categoriaSomas = new ArrayList<>();

        for(int i=0; i<categorias.size(); i++){
            HashMap<String, Double> tempHashmap = new HashMap<>();
            String tempCategoria = categorias.get(i).getCategoria();
            Double tempSoma = 0.0;

            for (Gasto gasto : categorias.get(i).getGastos()) {
                tempSoma += gasto.getValor();
            }

            valorTotal += tempSoma;
            tempHashmap.put(tempCategoria, tempSoma);
            categoriaSomas.add(tempHashmap);

        }

        for (HashMap<String, Double> categoriaSoma : categoriaSomas) {
            for(Map.Entry<String, Double> entry: categoriaSoma.entrySet()){
                Double porcentagem = (categoriaSoma.get(entry.getKey()) * 100.0) / valorTotal;
                categoriaSomas.get(0).put(entry.getKey(), porcentagem);
            }
        }

        System.out.println(categoriaSomas.get(0));
        return categoriaSomas.get(0);
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
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if(optional.isPresent()){
            Categoria categoria = categoriaRepository.getOne(id);

            System.out.println("GASTOS" + categoria.getGastos());
            System.out.println("RENDAS" + categoria.getRendas());

            return ResponseEntity.ok().body(new DetalhesCategoriaDto(categoria));
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
