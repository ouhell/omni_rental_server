package dev.codebaker.omni_rental.controllers.v1.dto.params;

public record GetPropertiesFilterParams(
        int priceMax,
        int priceMin,
        String nameLike,
        int page,
        int pageSize
) {
}
