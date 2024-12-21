package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = "application/vnd.api.v1+json")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<CreateProductResponse>> getAllProducts() {
        List<CreateProductResponse> products = productApplicationService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        CreateProductResponse createProductResponse = productApplicationService.createProduct(createProductCommand);
        log.info("Product created: " + createProductResponse);
        return ResponseEntity.ok(createProductResponse);
    }

}
