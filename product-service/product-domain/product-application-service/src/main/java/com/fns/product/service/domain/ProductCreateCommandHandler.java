package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;
import com.fns.product.service.domain.entity.*;
import com.fns.product.service.domain.mapper.ProductDataMapper;
import com.fns.product.service.domain.ports.output.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCreateCommandHandler {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;
    private final ProductBrandRepository productBrandRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductColorsRepository productColorsRepository;
    private final ProductSizesRepository productSizesRepository;

    public ProductCreateCommandHandler(
            ProductRepository productRepository,
            ProductDataMapper productDataMapper,
            ProductBrandRepository productBrandRepository,
            ProductColorsRepository productColorsRepository,
            ProductCategoriesRepository productCategoriesRepository, ProductSizesRepository productSizesRepository
    ) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
        this.productBrandRepository = productBrandRepository;
        this.productCategoriesRepository = productCategoriesRepository;
        this.productColorsRepository = productColorsRepository;
        this.productSizesRepository = productSizesRepository;
    }

    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {

        Product product = productDataMapper.createProduct(createProductCommand);

        Product savedProduct = saveProduct(product);

        ProductBrand brand = productBrandRepository.findById(createProductCommand.getBrand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        ProductCategories category = productCategoriesRepository.findById(createProductCommand.getProduct_categories_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductColors color = productColorsRepository.findById(createProductCommand.getColor_id())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        ProductSizes size = productSizesRepository.findById(createProductCommand.getSize_id())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        return CreateProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .sku(savedProduct.getSku())
                .description(savedProduct.getDescription())
                .slug(savedProduct.getSlug())
                .gender(savedProduct.getGender())
                .sizes(size)
                .brand(brand)
                .productCategory(category)
                .color(color)
                .build();
    }

    private Product saveProduct(Product product) {
        try {
            // Attempt to save the product to the repository
            Product savedProduct = productRepository.saveProduct(product);

            if (savedProduct == null) {
                throw new RuntimeException("Failed to save product");
            }

            return savedProduct;

        } catch (Exception e) {
            log.error("Error while saving product: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product", e);
        }
    }

}
