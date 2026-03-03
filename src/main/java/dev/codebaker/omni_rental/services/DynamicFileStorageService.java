package dev.codebaker.omni_rental.services;

public interface DynamicFileStorageService extends FileStorageService {

    String createPresignedURL();
}
