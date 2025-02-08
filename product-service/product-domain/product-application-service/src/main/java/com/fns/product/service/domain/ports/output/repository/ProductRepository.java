package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductRepository {
    Product saveProduct(Product product);

    Page<Product> getProducts(Integer page, Integer size, String name, List<UUID> categoriesId, Double minPrice, Double maxPrice);

    Product getProductById(UUID id);

    void deleteProduct(UUID id);

    Page<Product> getPopularProduct(Integer page, Integer size);
}
