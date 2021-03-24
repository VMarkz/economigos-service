package br.com.vitor.usercrud.controler;

import br.com.vitor.usercrud.utils.FileIo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("economigos/files")
public class FileControler {

    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() throws IOException {
        FileIo.createFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(FileIo.writeFile()));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }



}
