package dev.codebaker.omni_rental.modal.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "uploaded_images")
public class UploadedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UploadedFile blurImageFile;

    @OneToOne
    private UploadedFile smallImageFile;
    @OneToOne
    private UploadedFile optimalImageFile;
    @OneToOne
    private UploadedFile largeImageFile;

    @Column(nullable = false)
    private String mimeType;

    private long uploaderId;

    @CreationTimestamp
    private LocalDateTime  createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
