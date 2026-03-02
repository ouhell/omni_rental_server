package dev.codebaker.omni_rental.dto;

import lombok.Builder;

@Builder
public record StoredFileData(
        String key,
        String storage
) {
}
