package dev.codebaker.omni_rental.dto;

import lombok.Builder;
import org.springframework.core.io.InputStreamResource;

@Builder
public record FetchedFileData(
        String mimeType,
        String name,
        long size,
        InputStreamResource resource
) {
}
