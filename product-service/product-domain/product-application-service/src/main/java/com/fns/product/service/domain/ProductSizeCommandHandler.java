package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.get.ProductSizeResponse;
import com.fns.product.service.domain.entity.ProductSizes;
import com.fns.product.service.domain.mapper.ProductSizeMapperDomain;
import com.fns.product.service.domain.ports.output.repository.ProductSizesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductSizeCommandHandler {

    private final ProductSizesRepository productSizesRepository;
    private final ProductSizeMapperDomain productSizeMapper;

    public ProductSizeCommandHandler(ProductSizesRepository productSizesRepository, ProductSizeMapperDomain productSizeMapper1) {
        this.productSizesRepository = productSizesRepository;
        this.productSizeMapper = productSizeMapper1;
    }

    public List<ProductSizeResponse> getAllProductSize() {
        List<ProductSizes> getAllProductSizes = getProductSize();

        return getAllProductSizes.stream()
                .map(productSizeMapper::productSizeResponse)
                .collect(Collectors.toList());

    }

    List<ProductSizes> getProductSize() {
        return productSizesRepository.findAllProductSizes();
    }
}
