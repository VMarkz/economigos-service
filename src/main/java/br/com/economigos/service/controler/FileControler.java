package br.com.economigos.service.controler;

import br.com.economigos.service.model.Contabil;
import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.repository.ContabilRepository;
import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;
import br.com.economigos.service.utils.FileIo;
import br.com.economigos.service.utils.GravaArquivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Formatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/files")
public class FileControler {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    RendaRepository rendaRepository;
    @Autowired
    GastoRepository gastoRepository;
    @Autowired
    ContabilRepository contabilRepository;


    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() throws IOException {
        String filename = "Rendas&Gastos.csv";
        List<Renda> rendas = rendaRepository.findAll();
        List<Gasto> gastos = gastoRepository.findAll();
        FileIo.createFile();
        FileIo.separarGastosRendas(rendas, gastos);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(FileIo.getFile()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }

    @GetMapping("/export/extrato/{dias}")
    public void getExtratoDias(@PathVariable Integer dias){
        if(dias == 90){
            System.out.println(contabilRepository.findByDataPagamento90());
        }
    }

}
