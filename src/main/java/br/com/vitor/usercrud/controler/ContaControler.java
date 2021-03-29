package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.controler.dto.ContaDto;
import br.com.vitor.usercrud.controler.dto.UsuarioDto;
import br.com.vitor.usercrud.controler.form.ContaForm;
import br.com.vitor.usercrud.controler.form.UsuarioForm;
import br.com.vitor.usercrud.model.Conta;
import br.com.vitor.usercrud.model.Usuario;
import br.com.vitor.usercrud.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/economigos/contas")
public class ContaControler {

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public List<ContaDto> listar(){
        List<Conta> contas = contaRepository.findAll();
        return ContaDto.converter(contas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
        Conta conta = form.converter();
        contaRepository.save(conta);

        URI uri = uriBuilder.path("economigos/contas/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

}
