package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.mapper.UploadedFileMapper;
import dev.codebaker.omni_rental.modal.entities.UploadedFile;
import dev.codebaker.omni_rental.repositories.UploadedFileRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
    @Transactional
    public FileResponse uploadFile(MultipartFile file)  {
      return this.uploadFile(file,false);

    }

    @Override
    @Transactional
    public FileResponse uploadFile(MultipartFile file, boolean temporary) {


        try {
            return this.uploadFile(file.getInputStream(), file.getOriginalFilename(), file.getSize(), file.getContentType(), temporary);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    @Transactional
    public FileResponse uploadFile(final InputStream inputStream ,final String fileName, long size,final String mimeType, boolean temporary) {
        try {
            final String savedFilePath = this.createFile(inputStream,fileName);

            final UploadedFile uploadedFile = new UploadedFile();

            uploadedFile.setHost("local");
            uploadedFile.setName(fileName);
            uploadedFile.setSize(size);
            uploadedFile.setMimeType(mimeType);
            uploadedFile.setKey(savedFilePath);
            uploadedFile.setTemporary(temporary);

            final UploadedFile createdUploadedFile = this.uploadedFileRepository.save(uploadedFile);

            return uploadedFileMapper.toResponse(createdUploadedFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String createFile(MultipartFile file) throws IOException {
       return this.createFile(file.getInputStream(),file.getOriginalFilename());
    }

    private String createFile(InputStream fileData , String fileName) throws IOException {
        String uploadedFileName = UUID.randomUUID().toString() +  "." + StringUtils.getFilenameExtension(fileName);
        Files.copy(fileData,Path.of(UPLOAD_PATH.toString(),uploadedFileName));
        return uploadedFileName;
    }
}
