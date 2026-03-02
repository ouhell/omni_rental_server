package dev.codebaker.omni_rental.controllers.v1.dto.responses;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ImageResponse(
        FileResponse blurImageFile,
        FileResponse smallImageFile,
        FileResponse optimalImageFile,
        FileResponse largeImageFile,
        String mimeType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
