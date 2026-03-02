package dev.codebaker.omni_rental.modal;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Location {
    @Column(nullable = false)
    double longitude;
    @Column(nullable = false)
    double latitude;
    String region;
    String country;

}
