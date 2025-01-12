package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.entity.*;
import com.fns.product.service.domain.ports.output.repository.*;
import com.fns.product.service.domain.valueObject.Gender;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

    private final ProductBrandRepository productBrandRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductColorsRepository productColorsRepository;
    private final ProductSizesColorBrandRepository productSizesColorBrandRepository;
    private final ProductImageRepository productImagesRepository;

    public ProductDataMapper(ProductBrandRepository productBrandRepository, ProductCategoriesRepository productCategoriesRepository, ProductColorsRepository productColorsRepository, ProductSizesColorBrandRepository productSizesColorBrandRepository, ProductImageRepository productImagesRepository) {
        this.productBrandRepository = productBrandRepository;
        this.productCategoriesRepository = productCategoriesRepository;
        this.productColorsRepository = productColorsRepository;
        this.productSizesColorBrandRepository = productSizesColorBrandRepository;
        this.productImagesRepository = productImagesRepository;
    }

    public ProductResponse createProductResponse(CreateProductCommand createProductCommand, Product savedProduct) {


        ProductBrand brand = productBrandRepository.findById(createProductCommand.getBrand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        ProductCategories category = productCategoriesRepository.findById(createProductCommand.getProduct_categories_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductColors color = productColorsRepository.findById(createProductCommand.getColor_id())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        ProductSizes size = productSizesColorBrandRepository.findById(createProductCommand.getSize_id())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        return ProductResponse.builder()
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

    public Product createProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .id(createProductCommand.getId())
                .sku(createProductCommand.getSku())
                .name(createProductCommand.getName())
                .gender(new Gender(createProductCommand.getGender()).toStringValue())
                .description(createProductCommand.getDescription())
                .brandId(createProductCommand.getBrand_id())
                .productCategoryId(createProductCommand.getProduct_categories_id())
                .sizeId(createProductCommand.getSize_id())
                .colorId(createProductCommand.getColor_id())
                .build();
    }

    public ProductResponse editProductResponse(EditProductCommand editProductCommand, Product savedProduct) {

        ProductBrand brand = productBrandRepository.findById(editProductCommand.getBrand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        ProductCategories category = productCategoriesRepository.findById(editProductCommand.getProduct_categories_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductColors color = productColorsRepository.findById(editProductCommand.getColor_id())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        ProductSizes size = productSizesColorBrandRepository.findById(editProductCommand.getSize_id())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        return ProductResponse.builder()
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

}
