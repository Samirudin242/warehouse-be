package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.mapper.ProductDataMapper;
import com.fns.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCreateCommandHandler {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;

    public ProductCreateCommandHandler(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {

        CreateProductResponse createResponse = productDataMapper.createProductResponse(createProductCommand);

        Product product = productDataMapper.createProduct(createProductCommand);

        saveProduct(product);

        return createResponse;
    }

    private void saveProduct(Product product) {
        try {
            Product productResult = productRepository.saveProduct(product);

            if (productResult == null) {
                log.error("Failed to save product. The product entity was not persisted.");
                throw new RuntimeException("Failed to save product");
            }

            log.info("Successfully saved product with id: {}", productResult.getId());
        } catch (Exception e) {
            log.error("Error while saving product: {}", e.getMessage());
            throw new RuntimeException("Error while saving product", e);
        }
    }
}
