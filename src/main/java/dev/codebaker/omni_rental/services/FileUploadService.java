package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);
    FileResponse uploadFile(MultipartFile file,boolean temporary);

    FileResponse uploadFile(InputStream inputStream, String fileName, long size, String mimeType, boolean temporary);



}
