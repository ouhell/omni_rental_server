package dev.codebaker.omni_rental.repositories;

import dev.codebaker.omni_rental.modal.entities.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository  extends JpaRepository<UploadedFile, Long> {
}
