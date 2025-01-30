package com.fns.warehouse.service.domain.ports.input.message;

import com.fns.warehouse.service.domain.dto.message.ProductModel;

public interface ProductMessageListener {
    void savedProduct(ProductModel productModel);
}
