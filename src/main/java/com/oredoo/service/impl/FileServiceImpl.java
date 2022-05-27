package com.oredoo.service.impl;

import com.oredoo.model.File;
import com.oredoo.repository.FileRepository;
import com.oredoo.response.Response;
import com.oredoo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Response saveFile(MultipartFile uploadFile) throws IOException {
        String fileName = "";
        if (uploadFile.getOriginalFilename() != null) {
            fileName = StringUtils.cleanPath(uploadFile.getOriginalFilename());
        }
        String id = UUID.randomUUID().toString();
        String imageUrl = "http://localhost:8080/api/file/" + id;
        File file = new File(id, fileName, uploadFile.getContentType(), uploadFile.getBytes(), imageUrl);
        fileRepository.save(file);
        return new Response(HttpStatus.OK.value(), file, "Upload successfully");
    }

    @Override
    public Response getFile(String id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        return optionalFile.map(file -> new Response(HttpStatus.OK.value(), file, "File found"))
            .orElseGet(() -> new Response(HttpStatus.NOT_FOUND.value(), null, "File not found"));
    }
}
