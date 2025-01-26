package com.fns.product.service.domain.dto.delete;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DeleteCartItemResponse {
    private final String message;
    private final UUID cartItemId;
}

