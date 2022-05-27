package com.oredoo.controller;

import com.oredoo.model.File;
import com.oredoo.response.Response;
import com.oredoo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/file")
public class FileController {

    @Autowired
    private FileService fileService;

//        @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }

//    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Response response = fileService.getFile(id);
        File file = (File) response.getData();
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
            .body(file.getData());
    }
}
