package dev.codebaker.omni_rental.services;

import dev.codebaker.omni_rental.controllers.v1.dto.params.GetPropertiesFilterParams;
import dev.codebaker.omni_rental.controllers.v1.dto.requests.CreatePropertyRequest;
import dev.codebaker.omni_rental.controllers.v1.dto.responses.PropertyResponse;
import dev.codebaker.omni_rental.mapper.PropertyMapper;
import dev.codebaker.omni_rental.modal.entities.Property;
import dev.codebaker.omni_rental.repositories.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class PropertyManagementServiceImpl implements PropertyManagementService {

    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    @Override
    public PropertyResponse get(Long id) {
        var property =  propertyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user with id {%d} not found".formatted(id)));

        return propertyMapper.toResponse(property);

    }

    @Override
    public Page<PropertyResponse> getPage(GetPropertiesFilterParams params) {
        var page = propertyRepository.findAll(PageRequest.of(params.page(),params.pageSize()));

        return page.map(propertyMapper::toResponse);
    }

    @Override
    public PropertyResponse create(CreatePropertyRequest request) {
        var newProperty = new Property();
        newProperty.setName(request.name());
        newProperty.setDescription(request.description());

        return  propertyMapper.toResponse(propertyRepository.save(newProperty));
    }


}
