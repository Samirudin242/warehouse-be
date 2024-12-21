package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;
import com.fns.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Validated
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private  final ProductCreateCommandHandler productCreateCommandHandler;

    public ProductApplicationServiceImpl(ProductCreateCommandHandler productCreateCommandHandler) {
        this.productCreateCommandHandler = productCreateCommandHandler;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {
        return productCreateCommandHandler.createProduct(createProductCommand);
    }

    @Override
    public List<CreateProductResponse> getProducts() {
        return productCreateCommandHandler.getAllProducts();
    }
}
