package com.fns.warehouse.service.domain;

import com.fns.warehouse.service.domain.dto.message.ProductModel;
import com.fns.warehouse.service.domain.mapper.ProductDataMapper;
import com.fns.warehouse.service.domain.ports.input.message.ProductMessageListener;
import com.fns.warehouse.service.domain.ports.output.repository.ProductRepository;
import com.fns.warehouse.service.domain.ports.output.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductMessageListenerImpl implements ProductMessageListener {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;
    private final StockRepository stockRepository;

    public ProductMessageListenerImpl(ProductRepository productRepository, ProductDataMapper productDataMapper, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
        this.stockRepository = stockRepository;
    }

    @Override
    public void savedProduct(ProductModel productModel) {
        productRepository.save(productDataMapper.productModelToProduct(productModel));
        stockRepository.saveStock(productDataMapper.productModelToStock(productModel));
    }
}
