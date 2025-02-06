package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.mapper.ProductMapper;
import com.fns.product.service.dataaccess.repository.ProductCategoriesJpaRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@Service
public class ProductServiceImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductCategoriesJpaRepository productCategoriesJpaRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductJpaRepository productJpaRepository, ProductCategoriesJpaRepository productCategoriesJpaRepository, ProductMapper productMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productCategoriesJpaRepository = productCategoriesJpaRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = productMapper.productTotProductEntity(product);
        ProductEntity savedProduct = productJpaRepository.save(productEntity);

        return productMapper.productFromProductEntity(savedProduct);
    }

    private void collectCategoryIds(UUID categoryId, List<UUID> catIds) {
        // Always add the current category ID first
        catIds.add(categoryId);

        // Fetch child categories
        List<ProductCategoriesEntity> childCategories = productCategoriesJpaRepository.findByParentId(categoryId);

        // Recursively collect child category IDs
        for (ProductCategoriesEntity child : childCategories) {
            collectCategoryIds(child.getId(), catIds);
        }
    }


    @Override
    public Page<Product> getProducts(Integer page, Integer size, String name, List<UUID> categoryIds, Double minPrice, Double maxPrice) {

        Double minP = (minPrice == null || minPrice < 1) ? 0 : minPrice;
        Double maxP = (maxPrice == null || maxPrice < 1) ? 1000000000 : maxPrice;



        List<UUID> catIds = new ArrayList<>();

        if(categoryIds != null) {
            for (UUID categoryId : categoryIds) {
                collectCategoryIds(categoryId, catIds);
            }
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<ProductEntity> productEntities;

        if(name.isEmpty() && catIds.isEmpty()) {
            productEntities  = productJpaRepository.findAll(pageable);
        } else if(!catIds.isEmpty() && name.isEmpty()) {
            productEntities = productJpaRepository.findByCategoryIds(catIds, pageable);
        } else if(catIds.isEmpty()) {
            productEntities = productJpaRepository.findByNameILike(name, pageable);
        } else{
            productEntities = productJpaRepository.findByNameAndCategoryIds(name, catIds, pageable);
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
