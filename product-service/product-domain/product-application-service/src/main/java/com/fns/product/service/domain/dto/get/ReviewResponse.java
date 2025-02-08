package com.fns.product.service.domain.dto.get;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Builder
public class ReviewResponse {
    private UUID id;

    private String customerName;

    private String profileUrl;

    private Integer rating;

    private Integer count;

    private String comment;

    private UUID product_id;

    private LocalDateTime createdAt;
}

