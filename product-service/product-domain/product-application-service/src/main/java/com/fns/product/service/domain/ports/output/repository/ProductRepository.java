package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Product;

public interface ProductRepository {
    Product saveProduct(Product product);
}
