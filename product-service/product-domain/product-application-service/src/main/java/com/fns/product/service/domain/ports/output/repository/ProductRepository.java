package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product saveProduct(Product product);

    List<Product> getProducts();

    Product getProductById(UUID id);

    String deleteProduct(UUID id);
}
