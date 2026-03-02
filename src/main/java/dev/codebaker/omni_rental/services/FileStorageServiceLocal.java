package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.dto.StoredFileData;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileStorageServiceLocal implements FileStorageService {

    private  final Path UPLOAD_PATH = Path.of("files","uploads");

    @PostConstruct
    public void init() {
        if(!UPLOAD_PATH.toFile().exists()) {
            try {
                Files.createDirectories(UPLOAD_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public StoredFileData storeFile(MultipartFile file) {
        try {
            return storeFile(file.getInputStream(),file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StoredFileData storeFile(InputStream inputStream, String fileName) {
        String uploadedFileName = UUID.randomUUID().toString() +  "." + StringUtils.getFilenameExtension(fileName);
        try {
            Files.copy(inputStream, Path.of(UPLOAD_PATH.toString(),uploadedFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new StoredFileData(uploadedFileName,"local");
    }

    @Override
    public InputStreamResource fetchFile(String key) {
        try {
            return new InputStreamResource(Files.newInputStream(UPLOAD_PATH.resolve(key)));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }


}
