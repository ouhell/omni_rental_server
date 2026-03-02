package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.params.GetPropertiesFilterParams;
import dev.codebaker.omni_rental.controllers.v1.dto.requests.CreatePropertyRequest;
import dev.codebaker.omni_rental.controllers.v1.dto.responses.PropertyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyManagementService {
    PropertyResponse get(Long id);
    Page<PropertyResponse> getPage(GetPropertiesFilterParams params);
    PropertyResponse create(CreatePropertyRequest request);

}
