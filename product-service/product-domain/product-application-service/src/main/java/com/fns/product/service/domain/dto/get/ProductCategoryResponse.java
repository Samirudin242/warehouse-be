package com.fns.product.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder(toBuilder = true) // Allows modifying an existing object using the builder pattern
public class ProductCategoryResponse {
    private final UUID id;
    private final String name;
    private final UUID parentId;
    private final String slug;
    private final List<ProductCategoryResponse> children;

    // Add a default children list for immutability
    public static class ProductCategoryResponseBuilder {
        private List<ProductCategoryResponse> children = new ArrayList<>();
    }
}
