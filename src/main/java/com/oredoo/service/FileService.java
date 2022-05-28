package com.oredoo.service;

import com.oredoo.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    Response saveFile(MultipartFile file) throws IOException;

    Response getFile(String id);
}
