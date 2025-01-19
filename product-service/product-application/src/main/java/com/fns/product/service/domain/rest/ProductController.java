package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import com.fns.product.service.domain.ports.input.service.ProductPhotoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = "application/vnd.api.v1+json")
public class ProductController {

    private final ProductApplicationService productApplicationService;
    private final ProductPhotoService productPhotoService;

    public ProductController(ProductApplicationService productApplicationService, ProductPhotoService productPhotoService) {
        this.productApplicationService = productApplicationService;
        this.productPhotoService = productPhotoService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "") String name
    ) {
        Page<ProductResponse> products = productApplicationService.getProducts(page, size, name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products-name")
    public ResponseEntity<Page<ProductResponse>> getAllProductName(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "") String name
    ) {
        Page<ProductResponse> products = productApplicationService.getProducts(page, size, name);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        ProductResponse product = productApplicationService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(
            @PathVariable UUID id,
            @RequestBody @Valid EditProductCommand editProductCommand) {
        ProductResponse updatedProduct = productApplicationService.editProductById(id, editProductCommand);
        return ResponseEntity.ok(updatedProduct);
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        ProductResponse productResponse = productApplicationService.createProduct(createProductCommand);
        log.info("Product created: " + productResponse);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        try {
            String product = productApplicationService.deleteProductById(id);
            return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
        } catch (RuntimeException e) {
            log.error("Error in deleteProduct API: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete product: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/photo")
    public ResponseEntity<String> uploadProductPhoto(
            @PathVariable UUID id,
            @RequestParam("file") MultipartFile file) {
        try {
            // Upload the photo and get the URL
            String photoUrl = productPhotoService.uploadProductPhoto(id, file);
            log.info("Photo uploaded successfully for Product ID {}: {}", id, photoUrl);
            return ResponseEntity.ok(photoUrl);
        } catch (IOException e) {
            log.error("Error uploading photo for Product ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload photo: " + e.getMessage());
        }
    }
}
