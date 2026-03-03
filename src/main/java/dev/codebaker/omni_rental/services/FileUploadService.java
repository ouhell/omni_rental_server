package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.dto.FetchedFileData;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileUploadService {

    FileResponse uploadFile(MultipartFile file);
    FileResponse uploadFile(MultipartFile file,boolean temporary);

    FetchedFileData getFile(String key);

    void deleteFile(String key);

    String getPresignedUrl(String key);


}
