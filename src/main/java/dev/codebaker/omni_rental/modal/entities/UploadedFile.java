package dev.codebaker.omni_rental.modal.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_files")
@Data
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String key;

    private String url;

    private long size;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String host;

    private long uploaderId;

    @Column(nullable = false)
    private boolean temporary;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
