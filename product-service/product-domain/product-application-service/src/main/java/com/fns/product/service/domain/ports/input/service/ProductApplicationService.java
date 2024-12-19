package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.CreateProductResponse;

import javax.validation.Valid;

public interface ProductApplicationService {
    CreateProductResponse createProduct (@Valid CreateProductCommand createProductCommand);
}
