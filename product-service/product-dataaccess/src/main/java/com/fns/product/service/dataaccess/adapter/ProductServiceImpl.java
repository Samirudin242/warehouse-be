package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.mapper.ProductMapper;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@Service
public class ProductServiceImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductJpaRepository productJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productMapper.productTotProductEntity(product);
        ProductEntity savedProduct = productJpaRepository.save(productEntity);

        return productMapper.productFromProductEntity(savedProduct);
    }

    @Override
    public Page<Product> getProducts(Integer page, Integer size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ProductEntity> productEntities;

        log.info("product name is {}", name);
        if(name.isEmpty()) {
            productEntities  = productJpaRepository.findAll(pageable);
        } else {
            productEntities = productJpaRepository.findByNameILike(name, pageable);
        }

        List<Product> products = productEntities.stream()
                .map(productMapper::productFromProductEntity)
                .collect(Collectors.toList());

        return new PageImpl<>(products, pageable, productEntities.getTotalElements());
    }

    @Override
    public Product getProductById(UUID id) {
        return productJpaRepository.findById(id)
                .map(productMapper::productFromProductEntity)
                .orElseThrow(() -> new RuntimeException("Product not found for ID: " + id));
    }

    @Override
    public void deleteProduct(UUID id) {
        productJpaRepository.deleteById(id);
    }
}
