package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ShippingOrderResponse {
    private UUID order_id;
    private String username;
    private String user_address;
    private String message;
}
