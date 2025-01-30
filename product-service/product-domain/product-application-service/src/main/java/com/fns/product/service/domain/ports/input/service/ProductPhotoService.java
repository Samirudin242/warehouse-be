package com.fns.product.service.domain.ports.input.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ProductPhotoService {
    String uploadProductPhoto(UUID productId, MultipartFile file) throws IOException;
}
