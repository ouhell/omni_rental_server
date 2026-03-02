package dev.codebaker.omni_rental.repositories;

import dev.codebaker.omni_rental.modal.entities.UploadedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedImageRepository extends JpaRepository<UploadedImage, Long> {
}
