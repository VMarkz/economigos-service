package br.com.economigos.service.controler;

import br.com.economigos.service.controler.dto.*;
import br.com.economigos.service.controler.form.ContaForm;
import br.com.economigos.service.model.*;
import br.com.economigos.service.repository.ContaRepository;
import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;
import br.com.economigos.service.repository.UsuarioRepository;
import br.com.economigos.service.utils.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/contas")

public class ContaControler {

    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    RendaRepository rendaRepository;
    @Autowired
    ContaRepository contaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public List<ContaDto> listar(){
        List<Conta> contas = contaRepository.findAll();
        return ContaDto.converter(contas);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ContaDto>> listar(@PathVariable Long idUsuario){
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

        if (optional.isPresent()){
            List<Conta> contas = contaRepository.findAllByUsuario(idUsuario);
            return ResponseEntity.ok().body(ContaDto.converter(contas));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{idConta}/ultimos-meses")
    public ResponseEntity<List<ValorMensalTipoDto>> contabilPorMes(@PathVariable Long idConta) {
        Optional<Conta> optionalConta = contaRepository.findById(idConta);
        if (optionalConta.isPresent()){
            List<ValorMensalDto> valorMensalGastosDtos = new ArrayList<>();
            List<ValorMensalDto> valorMensalRendasDtos = new ArrayList<>();
            List<ValorMensalTipoDto> valorMensalTipoDtos = new ArrayList<>();

            for (int i = 1; i <= 3; i++) {
                LocalDate localDate = LocalDate.now().minusMonths(i);
                String anoMes = localDate.toString().substring(0, 7);
                String mes = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

                valorMensalGastosDtos.add(new ValorMensalDto(mes, Gasto.getUltimosMeses(anoMes, gastoRepository, idConta)));
                valorMensalRendasDtos.add(new ValorMensalDto(mes, Renda.getUltimosMeses(anoMes, rendaRepository, idConta)));
            }

            valorMensalTipoDtos.add(new ValorMensalTipoDto("Gasto", valorMensalGastosDtos));
            valorMensalTipoDtos.add(new ValorMensalTipoDto("Renda", valorMensalRendasDtos));
            return ResponseEntity.ok().body(valorMensalTipoDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm form, UriComponentsBuilder uriBuilder) {
        Conta conta = form.converter(usuarioRepository);
        contaRepository.save(conta);
        conta.addObserver(new Usuario());
        conta.notificaObservador("create");

        URI uri = uriBuilder.path("economigos/contas/{id}").buildAndExpand(conta.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContaDto(conta));
    }

    @GetMapping("/{id}/usuario/{idUsuario}")
    public ResponseEntity<DetalhesContaDto> detalhar(@PathVariable Long id, @PathVariable Long idUsuario){
        Optional<Conta> optionalConta = contaRepository.findById(id);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Optional<Conta> optionalContaUsuario = contaRepository.findContaByUsuario(id, idUsuario);
        if(optionalConta.isPresent() && optionalUsuario.isPresent() && optionalContaUsuario.isPresent()){
            Conta conta = contaRepository.getOne(id);
            DetalhesContaDto detalhesContaDto = new DetalhesContaDto(conta);
            for (Gasto gasto : contaRepository.getOne(id).getGastos()) {
                  detalhesContaDto.setTotalGastos(detalhesContaDto.getTotalGastos() + gasto.getValor());
            }
            for (Renda renda : contaRepository.getOne(id).getRendas()) {
                detalhesContaDto.setTotalRendas(detalhesContaDto.getTotalRendas() + renda.getValor());
            }
            return ResponseEntity.ok().body(detalhesContaDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/ultimas-atividades")
    public Page<ContabilUltimasAtividadesDto> ultimasAtividades(@PathVariable Long id, Pageable pageable){
        List<Renda> rendas = rendaRepository.findRendaByConta(id);
        List<Gasto> gastos = gastoRepository.findGastoByConta(id);
        List<ContabilUltimasAtividadesDto> list = new ArrayList<>();
        PilhaObj<ContabilUltimasAtividadesDto> pile= new PilhaObj<>(5);

        for(Renda renda : rendas){
            list.add(new ContabilUltimasAtividadesDto(renda.getDescricao(),
                    renda.getDataPagamento(), renda.getValor(), renda.getTipo()));
        }
        for(Gasto gasto : gastos){
            list.add(new ContabilUltimasAtividadesDto(gasto.getDescricao(),
                    gasto.getDataPagamento(), gasto.getValor(), gasto.getTipo()));
        }
        
        Page page = new PageImpl(list);
        return page;
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ContaDto> alterar(@PathVariable Long id, @RequestBody @Valid ContaForm form){
        Optional<Conta> optional = contaRepository.findById(id);
        if (optional.isPresent()) {
            Conta conta = form.atualizar(id, contaRepository);
            conta.addObserver(new Usuario());
            conta.notificaObservador("update");
            return ResponseEntity.ok(new ContaDto(conta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id){
        Optional<Conta> optional = contaRepository.findById(id);
        if(optional.isPresent()){
            Conta conta = contaRepository.getOne(id);
            conta.addObserver(new Usuario());
            contaRepository.deleteById(id);
            conta.notificaObservador("create");
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
