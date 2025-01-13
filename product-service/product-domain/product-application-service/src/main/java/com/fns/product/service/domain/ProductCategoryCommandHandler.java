package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.entity.ProductCategories;
import com.fns.product.service.domain.mapper.ProductCategoryDomainMapper;
import com.fns.product.service.domain.ports.output.repository.ProductCategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

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

        return buildCategoryHierarchy(allCategories);
    }

    List<ProductCategories> getCategories() {
        return productCategoriesRepository.findAllCategories();
    }

    private List<ProductCategoryResponse> buildCategoryHierarchy(List<ProductCategories> flatCategories) {
        Map<UUID, ProductCategoryResponse> categoryMap = flatCategories.stream()
                .map(productCategoryDomainMapper::productCategoryResponse)
                .collect(Collectors.toMap(ProductCategoryResponse::getId, category -> category));

        List<ProductCategoryResponse> roots = new ArrayList<>();

        for (ProductCategoryResponse category : categoryMap.values()) {
            if (category.getParentId() == null) {
                roots.add(category);
            } else {
                ProductCategoryResponse parent = categoryMap.get(category.getParentId());
                if (parent != null) {
                    parent.getChildren().add(category);
                }
            }
        }

        return roots;
    }
}
