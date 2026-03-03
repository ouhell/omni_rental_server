package dev.codebaker.omni_rental.controllers.v1;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileUploadService  fileUploadService;


    @GetMapping("/{key}")
    public ResponseEntity<Resource> download(@PathVariable String key) {

        var fetchedFileData = fileUploadService.getFile(key);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fetchedFileData.mimeType()))
                .body(fetchedFileData.resource());
    }
    
    
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<FileResponse> post(@RequestPart("file")  MultipartFile file) {
         var result = this.fileUploadService.uploadFile(file);

         return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
         this.fileUploadService.deleteFile(key);

         return ResponseEntity.noContent().build();
    }
    
    
    
    
}
