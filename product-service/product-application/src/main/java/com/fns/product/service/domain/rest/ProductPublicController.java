package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "product-public", produces = "application/vnd.api.v1+json")
public class ProductPublicController {
    private final ProductApplicationService productApplicationService;

    public ProductPublicController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false) List<UUID> categoryIds
    ) {
        Double minP = 0.0;
        Double maxP = 0.0;
        Page<ProductResponse> products = productApplicationService.getProducts(page, size, name, categoryIds, minP, maxP);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        ProductResponse product = productApplicationService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/products-category")
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategory() {
        List<ProductCategoryResponse> products = productApplicationService.getProductCategory();
        return ResponseEntity.ok(products);
    }

}
