package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductCommandHandler productCommandHandler;
    private final ProductSizeBrandColorCommandHandler productSizeBrandColorCommandHandler;
    private final ProductCategoryCommandHandler productCategoryCommandHandler;

    public ProductApplicationServiceImpl(ProductCommandHandler productCommandHandler, ProductSizeBrandColorCommandHandler productSizeBrandColorCommandHandler, ProductCategoryCommandHandler productCategoryCommandHandler) {
        this.productCommandHandler = productCommandHandler;
        this.productSizeBrandColorCommandHandler = productSizeBrandColorCommandHandler;
        this.productCategoryCommandHandler = productCategoryCommandHandler;
    }

    @Override
    public ProductResponse createProduct(CreateProductCommand createProductCommand) {
        return productCommandHandler.createProduct(createProductCommand);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productCommandHandler.getAllProducts();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        return productCommandHandler.getProductById(id);
    }

    @Override
    public ProductResponse editProductById(UUID id, EditProductCommand editProductCommand) {
        return productCommandHandler.editProductById(id, editProductCommand);
    }

    @Override
    public String deleteProductById(UUID id) {
        return productCommandHandler.deleteProduct(id);
    }

    @Override
    public List<ProductSizeBrandColorResponse> getProductSize() {
        return productSizeBrandColorCommandHandler.getAllProductSize();
    }

    @Override
    public List<ProductCategoryResponse> getProductCategory() {
        return productCategoryCommandHandler.getProductCategories();
    }

    @Override
    public List<ProductSizeBrandColorResponse> getProductBrand() {
        return productSizeBrandColorCommandHandler.getAllProductBrand();
    }

    @Override
    public List<ProductSizeBrandColorResponse> getProductColor() {
        return productSizeBrandColorCommandHandler.getAllProductColor();
    }
}
