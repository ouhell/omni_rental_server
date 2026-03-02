package dev.codebaker.omni_rental.controllers.v1;

import dev.codebaker.omni_rental.controllers.v1.dto.params.GetPropertiesFilterParams;
import dev.codebaker.omni_rental.controllers.v1.dto.requests.CreatePropertyRequest;
import dev.codebaker.omni_rental.controllers.v1.dto.responses.PropertyResponse;
import dev.codebaker.omni_rental.repositories.PropertyRepository;
import dev.codebaker.omni_rental.services.PropertyManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertiesController {
    private final PropertyManagementService propertyManagementService;

    @GetMapping()
    public ResponseEntity<Page<PropertyResponse>> get(@ModelAttribute GetPropertiesFilterParams filterParams) {
        return ResponseEntity.ok().body(propertyManagementService.getPage(filterParams));

    }

    @PostMapping()
    public ResponseEntity<PropertyResponse> getById(@RequestBody CreatePropertyRequest request) {

        var createdProperty = propertyManagementService.create(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdProperty.id()).toUri();

        return ResponseEntity.created(uri).body(createdProperty);

    }
}
