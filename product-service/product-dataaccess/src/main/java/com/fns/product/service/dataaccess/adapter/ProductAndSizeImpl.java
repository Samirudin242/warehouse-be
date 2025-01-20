package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductAndSizeEntity;
import com.fns.product.service.dataaccess.mapper.ProductAndSizeMapper;
import com.fns.product.service.dataaccess.repository.ProductAndSizeJpaRepository;
import com.fns.product.service.domain.entity.ProductAndSize;
import com.fns.product.service.domain.ports.output.repository.ProductAndSizeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class ProductAndSizeImpl implements ProductAndSizeRepository {
    private final ProductAndSizeJpaRepository productAndSizeJpaRepository;
    private final ProductAndSizeMapper productAndSizeMapper;

    public ProductAndSizeImpl(ProductAndSizeJpaRepository productAndSizeJpaRepository, ProductAndSizeMapper productAndSizeMapper) {
        this.productAndSizeJpaRepository = productAndSizeJpaRepository;
        this.productAndSizeMapper = productAndSizeMapper;
    }

    @Override
    public void save(ProductAndSize productAndSize) {
        ProductAndSizeEntity entity = productAndSizeMapper.productAndSizeEntity(productAndSize);
        productAndSizeJpaRepository.save(entity);
    }

    @Override
    @Transactional
    public List<ProductAndSize> getAllByProductId(UUID productId) {
        List<ProductAndSizeEntity> entities = productAndSizeJpaRepository.findByProduct_Id(productId);
        return entities.stream()
                .map(entity -> new ProductAndSize(
                        entity.getId(),
                        entity.getProduct().getId(),
                        entity.getSize().getId(),
                        entity.getSize().getSize()
                ))
                .toList();
    }
}
