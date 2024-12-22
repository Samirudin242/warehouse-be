package com.fns.product.service.domain;

import com.fns.product.service.domain.ports.output.repository.ProductPhotoRepository;

import com.fns.product.service.domain.ports.input.service.ProductPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {

     private final ProductPhotoRepository productPhotoRepository;

    public ProductPhotoServiceImpl(ProductPhotoRepository productPhotoRepository) {
        this.productPhotoRepository = productPhotoRepository;
    }


    @Override
    public String uploadProductPhoto(UUID productId, MultipartFile file) throws IOException {
        return productPhotoRepository.uploadProductPhoto(productId, file);
    }

}
