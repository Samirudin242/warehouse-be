package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductSizes {
    private UUID id;
    private String size;
    private Boolean isStock;

}