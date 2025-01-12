package com.fns.product.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class ProductSizeBrandColorResponse {
    private final UUID id;
    private final String size;
    private final String brand;
    private final String name;
}
