package com.fns.warehouse.service.domain.dto.get;

import com.fns.warehouse.service.domain.entity.Product;
import com.fns.warehouse.service.domain.entity.ProductColor;
import com.fns.warehouse.service.domain.entity.ProductSizes;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
public class OrderItemResponse {
    private UUID id;

    private Integer quantity;

    private Double price;

    private List<UUID> warehouse_id;

    private Product product;

    private ProductColor productColor;

    private ProductSizes productSize;
}
