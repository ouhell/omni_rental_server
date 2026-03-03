package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.dto.FetchedFileData;
import dev.codebaker.omni_rental.mapper.UploadedFileMapper;
import dev.codebaker.omni_rental.modal.entities.UploadedFile;
import dev.codebaker.omni_rental.repositories.UploadedFileRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceLocal implements FileUploadService {
    private final UploadedFileMapper uploadedFileMapper;
    private final UploadedFileRepository   uploadedFileRepository;
    private final DynamicFileStorageService fileStorageService;




    @Override
    @Transactional
    public FileResponse uploadFile(MultipartFile file)  {
      return this.uploadFile(file,false);

    }

    @Override
    @Transactional
    public FileResponse uploadFile(MultipartFile file, boolean temporary) {

        var storedFileData = fileStorageService.storeFile(file);
        final UploadedFile uploadedFile = new UploadedFile();

        uploadedFile.setHost(storedFileData.storage());
        uploadedFile.setName(file.getOriginalFilename());
        uploadedFile.setSize(file.getSize());
        uploadedFile.setMimeType(file.getContentType());
        uploadedFile.setKey(storedFileData.key());
        uploadedFile.setTemporary(temporary);

        final UploadedFile createdUploadedFile = this.uploadedFileRepository.save(uploadedFile);

        return uploadedFileMapper.toResponse(createdUploadedFile);


    }

    @Override
    public FetchedFileData getFile(String key) {
        var uploadedFile = uploadedFileRepository.findByKey(key).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var inputStreamResource = fileStorageService.fetchFile(key);

        return  FetchedFileData.builder()
                .name(uploadedFile.getName())
                .size(uploadedFile.getSize())
                .mimeType(uploadedFile.getMimeType())
                .resource(inputStreamResource)
                .build();


    }

    @Override
    @Transactional
    public void deleteFile(String key) {
        var uploadedFile = uploadedFileRepository.findByKey(key).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        uploadedFileRepository.delete(uploadedFile);

        fileStorageService.removeFile(key);

    }

    @Override
    public String getPresignedUrl(String key) {
        return fileStorageService.createPresignedURL(key);
    }


}
