package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import com.fns.product.service.domain.mapper.ProductSizeColorBrandMapperDomain;
import com.fns.product.service.domain.ports.output.repository.ProductSizesColorBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductSizeBrandColorCommandHandler {

    private final ProductSizesColorBrandRepository productSizesColorBrandRepository;
    private final ProductSizeColorBrandMapperDomain productSizeColorBrandMapper;

    public ProductSizeBrandColorCommandHandler(ProductSizesColorBrandRepository productSizesColorBrandRepository, ProductSizeColorBrandMapperDomain productSizeMapper1) {
        this.productSizesColorBrandRepository = productSizesColorBrandRepository;
        this.productSizeColorBrandMapper = productSizeMapper1;
    }

    public List<ProductSizeBrandColorResponse> getAllProductSize() {
        List<ProductSizes> getAllProductSizes = getProductSize();

        return getAllProductSizes.stream()
                .map(productSizeColorBrandMapper::productSizeResponse)
                .collect(Collectors.toList());

    }

    public List<ProductSizeBrandColorResponse> getAllProductBrand() {
        List<ProductBrand> getAllProductBrand = productSizesColorBrandRepository.findAllBrand();
        return getAllProductBrand.stream()
            .map(productSizeColorBrandMapper::productBrandResponse)
                .collect(Collectors.toList());
    }

    public List<ProductSizeBrandColorResponse> getAllProductColor() {
        List<ProductColors> getAllProductColor = productSizesColorBrandRepository.findAllColors();
        return getAllProductColor.stream()
                .map(productSizeColorBrandMapper::productColorResponse)
                .collect(Collectors.toList());
    }

    List<ProductSizes> getProductSize() {
        return productSizesColorBrandRepository.findAllProductSizes();
    }
}
