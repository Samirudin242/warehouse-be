package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.mapper.ProductSizeColorBrandMapper;
import com.fns.product.service.dataaccess.repository.ProductBrandJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import com.fns.product.service.domain.entity.ProductBrand;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import com.fns.product.service.domain.ports.output.repository.ProductSizesColorBrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductSizesColorBrandImpl implements ProductSizesColorBrandRepository {

    private final ProductSizeJpaRepository productSizesJpaRepository;
    private final ProductBrandJpaRepository productBrandJpaRepository;
    private final ProductColorsJpaRepository productColorsJpaRepository;
    private final ProductSizeColorBrandMapper productSizeColorBrandMapper;


    public ProductSizesColorBrandImpl(ProductSizeJpaRepository productSizesJpaRepository, ProductSizeColorBrandMapper productSizeColorBrandMapper, ProductBrandJpaRepository productBrandJpaRepository, ProductColorsJpaRepository productColorsJpaRepository) {
        this.productSizesJpaRepository = productSizesJpaRepository;
        this.productSizeColorBrandMapper = productSizeColorBrandMapper;
        this.productBrandJpaRepository = productBrandJpaRepository;
        this.productColorsJpaRepository = productColorsJpaRepository;
    }

    @Override
    public Optional<ProductSizes> findById(UUID sizeId) {
        Optional<ProductSizesEntity> optionalProductSizesEntity = productSizesJpaRepository.findById(sizeId);

        return optionalProductSizesEntity.map(productSizeColorBrandMapper::sizeFromSizeEntity);
    }

    @Override
    public List<ProductSizes> findAllProductSizes() {
        List<ProductSizesEntity> productSizes = productSizesJpaRepository.findAll();

        return productSizes.stream()
                .map(productSizeColorBrandMapper::sizeFromSizeEntity)
               .collect(Collectors.toList());
    }

    @Override
    public List<ProductBrand> findAllBrand() {
        List<ProductBrandEntity> productBrand = productBrandJpaRepository.findAll();
        return productBrand.stream()
                .map(productSizeColorBrandMapper::brandFromBrandEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductColors> findAllColors() {
        List<ProductColorsEntity> productBrand = productColorsJpaRepository.findAll();
        return productBrand.stream()
                .map(productSizeColorBrandMapper::productFromColorEntity)
                .collect(Collectors.toList());
    }
}
