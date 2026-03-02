package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {

    ImageResponse uploadImage(MultipartFile file);

    ImageResponse uploadImage(MultipartFile blur,MultipartFile small,MultipartFile optimal,MultipartFile large);

    ImageResponse linkImage(long blurId, long smallId, long optimalId, long large, String name);



}
