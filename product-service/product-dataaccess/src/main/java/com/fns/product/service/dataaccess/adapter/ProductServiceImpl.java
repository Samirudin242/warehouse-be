package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductServiceImpl implements ProductRepository {
    @Override
    public Product saveProduct(Product product) {
        return product;
    }
}
