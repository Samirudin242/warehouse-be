package com.fns.product.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductReviewCommand {
    private Integer rating;
    private String comment;
    private UUID product_id;
}
