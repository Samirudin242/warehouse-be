package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
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

    private  final ProductCreateCommandHandler productCreateCommandHandler;

    public ProductApplicationServiceImpl(ProductCreateCommandHandler productCreateCommandHandler) {
        this.productCreateCommandHandler = productCreateCommandHandler;
    }

    @Override
    public ProductResponse createProduct(CreateProductCommand createProductCommand) {
        return productCreateCommandHandler.createProduct(createProductCommand);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productCreateCommandHandler.getAllProducts();
    }

    @Override
    public ProductResponse getProductById(UUID id) {
        return productCreateCommandHandler.getProductById(id);
    }

    @Override
    public ProductResponse editProductById(UUID id, EditProductCommand editProductCommand) {
        return productCreateCommandHandler.editProductById(id, editProductCommand);
    }

    @Override
    public String deleteProductById(UUID id) {
        return productCreateCommandHandler.deleteProduct(id);
    }
}
