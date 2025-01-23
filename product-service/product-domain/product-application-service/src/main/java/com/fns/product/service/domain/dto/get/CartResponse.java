package com.fns.product.service.domain.dto.get;

import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CartResponse {

    private final UUID id;
    private final ProductColors colors;
    private final ProductSizes size;
    private final Product product;
    private final Integer quantity;
    private final Double price;

}
