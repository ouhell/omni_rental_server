package dev.codebaker.omni_rental.controllers.v1.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FileResponse(
        String name,
        String key,
        String url,
        String mimeType,
        long size,
        String host,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
