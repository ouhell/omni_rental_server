package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.dto.StoredFileData;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileStorageService {


    StoredFileData storeFile(MultipartFile file);

    StoredFileData storeFile(InputStream inputStream, String fileName,long size);

    InputStreamResource fetchFile(String key);
}
