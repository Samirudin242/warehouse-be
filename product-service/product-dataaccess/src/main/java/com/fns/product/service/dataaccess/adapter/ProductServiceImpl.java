package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.mapper.ProductMapper;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductServiceImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productMapper.productTotProductEntity(product);
        ProductEntity savedProduct = productJpaRepository.save(productEntity);

        return productMapper.productFromProductEntity(savedProduct);
    }

    @Override
    public List<Product> getProducts() {
        // Fetch all product entities from the database
        List<ProductEntity> productEntities = productJpaRepository.findAll();

        // Map the list of ProductEntity to a list of Product
        List<Product> products = productEntities.stream()
                .map(productMapper::productFromProductEntity)
                .collect(Collectors.toList());

        return products;
    }
}
