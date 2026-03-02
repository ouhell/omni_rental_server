package dev.codebaker.omni_rental.mapper;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.ImageResponse;
import dev.codebaker.omni_rental.modal.entities.UploadedImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UploadedImageMapper {
     ImageResponse toResponse(UploadedImage  uploadedImage);

}
