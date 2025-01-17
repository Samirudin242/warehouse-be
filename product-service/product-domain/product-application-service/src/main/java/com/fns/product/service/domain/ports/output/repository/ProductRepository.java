package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product saveProduct(Product product);

    Page<Product> getProducts(Integer page, Integer size, String name);

    Product getProductById(UUID id);

    void deleteProduct(UUID id);
}
