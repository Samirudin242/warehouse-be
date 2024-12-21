package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import com.fns.product.service.dataaccess.mapper.ProductCategoryMapper;
import com.fns.product.service.dataaccess.repository.ProductCategoriesJpaRepository;
import com.fns.product.service.domain.entity.ProductCategories;
import com.fns.product.service.domain.ports.output.repository.ProductCategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ProductCategoryImpl implements ProductCategoriesRepository {

    private final ProductCategoriesJpaRepository productCategoriesJpaRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryImpl(ProductCategoriesJpaRepository productCategoriesJpaRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoriesJpaRepository = productCategoriesJpaRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public Optional<ProductCategories> findById(UUID categoryId) {
        Optional<ProductCategoriesEntity> optionalProductCategoriesEntity = productCategoriesJpaRepository.findById(categoryId);
        return optionalProductCategoriesEntity.map(productCategoryMapper::categoryFromCategoryEntity);
    }
}
