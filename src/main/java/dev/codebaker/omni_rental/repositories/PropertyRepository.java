package dev.codebaker.omni_rental.repositories;

import dev.codebaker.omni_rental.modal.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepository extends JpaRepository<Property, Long> {

    
}
