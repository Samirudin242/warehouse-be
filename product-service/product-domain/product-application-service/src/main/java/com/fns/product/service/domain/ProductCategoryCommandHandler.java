package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.entity.ProductCategories;
import com.fns.product.service.domain.mapper.ProductCategoryDomainMapper;
import com.fns.product.service.domain.ports.output.repository.ProductCategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductCategoryCommandHandler {
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductCategoryDomainMapper productCategoryDomainMapper;

    public ProductCategoryCommandHandler(ProductCategoriesRepository productCategoriesRepository, ProductCategoryDomainMapper productCategoryDomainMapper) {
        this.productCategoriesRepository = productCategoriesRepository;
        this.productCategoryDomainMapper = productCategoryDomainMapper;
    }

    public List<ProductCategoryResponse> getProductCategories() {

        List<ProductCategories> allCategories = getCategories();

        return allCategories.stream()
                .map(productCategoryDomainMapper::productCategoryResponse)
               .toList();
    }

    List<ProductCategories> getCategories() {
        return productCategoriesRepository.findAllCategories();
    }
}
