package dev.codebaker.omni_rental.controllers.v1.dto.responses;

import java.time.LocalDateTime;

public record PropertyResponse(
        Long id,
        String name,
        String description,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
