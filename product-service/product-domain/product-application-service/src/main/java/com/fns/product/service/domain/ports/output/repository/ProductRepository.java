package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Product;

import java.util.List;

public interface ProductRepository {
    Product saveProduct(Product product);

    List<Product> getProducts();
}
