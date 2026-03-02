package dev.codebaker.omni_rental.mapper;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.FileResponse;
import dev.codebaker.omni_rental.modal.entities.UploadedFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UploadedFileMapper {
   FileResponse toResponse(UploadedFile uploadedFile);
}
