package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.dto.StoredFileData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service("s3")
@Primary
@RequiredArgsConstructor
public  class FileStorageServiceS3 implements DynamicFileStorageService {
    private final Region region;
    private final S3Client s3;
    private final S3Presigner presigner;
    private final String STORAGE = "s3";

    @Value("${aws.s3.bucket}")
    private  String bucketName;


    @Override
    public StoredFileData storeFile(MultipartFile file) {
        try {
            return this.storeFile(file.getInputStream(),file.getOriginalFilename(),file.getSize());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StoredFileData storeFile(InputStream inputStream, String fileName,long size) {
        var uniqueKey = UUID.randomUUID().toString() + "-" + fileName;
        PutObjectRequest putObjectRequest =  PutObjectRequest.builder().bucket(bucketName)
                .key(uniqueKey).build();
        var response = s3.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream,size));

        return StoredFileData.builder()
                .key(uniqueKey)
                .storage(STORAGE)
                .build();
    }

    @Override
    public InputStreamResource fetchFile(String key) {
        var resp = s3.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build());
        return new InputStreamResource(resp);


    }

    @Override
    public void removeFile(String key) {
        DeleteObjectRequest  deleteObjectRequest  =  DeleteObjectRequest.builder().bucket(bucketName).key(key).build();
        s3.deleteObject(deleteObjectRequest);
    }

    @Override
    public String createPresignedURL(String key) {
        GetObjectRequest getObjectRequest =  GetObjectRequest.builder().bucket(bucketName).key(key).build();
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.of(20, ChronoUnit.MINUTES))
                .getObjectRequest(getObjectRequest)
                .build();
        PresignedGetObjectRequest presignedGetObjectRequest = presigner.presignGetObject(getObjectPresignRequest);

        return presignedGetObjectRequest.url().toString();

        
    }
}
