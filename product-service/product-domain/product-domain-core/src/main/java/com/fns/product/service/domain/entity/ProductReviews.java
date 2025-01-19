package com.fns.product.service.domain.entity;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductReviews {

    private final UUID id;
    private final Integer rating;
    private final Integer count;
    private final String comment;
    private final UUID productId;
}
