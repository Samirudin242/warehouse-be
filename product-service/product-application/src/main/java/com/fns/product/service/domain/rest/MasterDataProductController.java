package com.fns.product.service.domain.rest;

import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "product", produces = "application/vnd.api.v1+json")
public class MasterDataProductController {

    private final ProductApplicationService productApplicationService;

    public MasterDataProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping("/products-size")
    public ResponseEntity<List<ProductSizeBrandColorResponse>> getAllProductSize() {
        List<ProductSizeBrandColorResponse> products = productApplicationService.getProductSize();
        return ResponseEntity.ok(products);
    }


    @GetMapping("/products-brand")
    public ResponseEntity<List<ProductSizeBrandColorResponse>> getAllProductBrand() {
        List<ProductSizeBrandColorResponse> products = productApplicationService.getProductBrand();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products-color")
    public ResponseEntity<List<ProductSizeBrandColorResponse>> getAllProductColor() {
        List<ProductSizeBrandColorResponse> products = productApplicationService.getProductColor();
        return ResponseEntity.ok(products);
    }

}
