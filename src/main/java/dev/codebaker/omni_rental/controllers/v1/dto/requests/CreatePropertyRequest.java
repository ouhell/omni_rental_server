package dev.codebaker.omni_rental.controllers.v1.dto.requests;

import lombok.Builder;

@Builder
public record CreatePropertyRequest(
         String name,
         String description
) {
}
