package com.fns.product.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ProductSizeBrandResponse {
    private final UUID id;
    private final String size;
}
