package com.fns.product.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ProductCategoryResponse {
    private final UUID id;
    private final String name;
    private final UUID parentId;
    private final String slug;
}
