package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.ProductPricesEntity;
import com.fns.product.service.dataaccess.mapper.ProductPriceMapper;
import com.fns.product.service.dataaccess.repository.ProductPricesJpaRepository;
import com.fns.product.service.domain.entity.ProductPrices;
import com.fns.product.service.domain.ports.output.repository.ProductPricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductPricesImpl implements ProductPricesRepository {

    private final ProductPriceMapper productPriceMapper;
    private  final ProductPricesJpaRepository productPricesJpaRepository;
    public ProductPricesImpl(ProductPriceMapper productPriceMapper, ProductPricesJpaRepository productPricesJpaRepository) {
        this.productPriceMapper = productPriceMapper;
        this.productPricesJpaRepository = productPricesJpaRepository;
    }

    @Override
    public ProductPrices savePrice(ProductPrices productPrices) {
        ProductPricesEntity productPricesEntity = productPriceMapper.priceToPriceEntity(productPrices);
        ProductPricesEntity savedProductPrices = productPricesJpaRepository.save(productPricesEntity);

        return productPriceMapper.priceFromPriceEntity(savedProductPrices);

    }
}
