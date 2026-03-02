package dev.codebaker.omni_rental.mapper;

import dev.codebaker.omni_rental.controllers.v1.dto.responses.PropertyResponse;
import dev.codebaker.omni_rental.modal.entities.Property;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

   PropertyResponse toResponse(Property property);

}
