package com.fns.product.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ReviewResponse {
    private UUID id;

    private Integer rating;

    private Integer count;

    private String comment;

    private UUID product_id;
}

