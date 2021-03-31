package br.com.economigos.service.controler;

import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;
import br.com.economigos.service.utils.CsvHelper;
import br.com.economigos.service.utils.FileIo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/economigos/files")
public class FileControler {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    RendaRepository rendaRepository;
    @Autowired
    GastoRepository gastoRepository;


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

    @GetMapping("/download/rendas")
    public ResponseEntity<Resource> getCsvRendas() {
        List<Renda> rendas = rendaRepository.findAll();
        String filename = "rendas.csv";
        InputStreamResource file = new InputStreamResource(CsvHelper.rendasToCSV(rendas));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping("/download/gastos")
    public ResponseEntity<Resource> getCsvGastos() {
        List<Gasto> gastos = gastoRepository.findAll();
        String filename = "gastos.csv";
        InputStreamResource file = new InputStreamResource(CsvHelper.gastosToCSV(gastos));
        System.out.println(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

}
