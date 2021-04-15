package br.com.economigos.service.controler;

import br.com.economigos.service.model.Gasto;
import br.com.economigos.service.model.Renda;
import br.com.economigos.service.repository.GastoRepository;
import br.com.economigos.service.repository.RendaRepository;
import br.com.economigos.service.utils.FileIo;
import br.com.economigos.service.utils.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() throws IOException {
        String filename = "rendas&gastos.csv";

        List<Renda> rendas = rendaRepository.findAll();
        ListaObj<Renda> listaObjRendas = new ListaObj(rendas.size());
        for (Renda renda : rendas) {
            listaObjRendas.adiciona(renda);
        }

        List<Gasto> gastos = gastoRepository.findAll();
        ListaObj<Gasto> listaObjGastos = new ListaObj(gastos.size());
        for (Gasto gasto : gastos) {
            listaObjGastos.adiciona(gasto);
        }

        FileIo.createFile();
        FileIo.separarGastosRendas(listaObjRendas, listaObjGastos);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(FileIo.getFile()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }

}
