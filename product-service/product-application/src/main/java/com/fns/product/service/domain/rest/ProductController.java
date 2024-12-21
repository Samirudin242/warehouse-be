package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = "application/vnd.api.v1+json")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productApplicationService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        ProductResponse product = productApplicationService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        ProductResponse productResponse = productApplicationService.createProduct(createProductCommand);
        log.info("Product created: " + productResponse);
        return ResponseEntity.ok(productResponse);
    }

}
