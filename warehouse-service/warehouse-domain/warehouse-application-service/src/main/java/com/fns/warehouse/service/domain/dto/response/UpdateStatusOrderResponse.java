package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UpdateStatusOrderResponse {
    private UUID order_id;
    private String message;
    private Date order_date;
    private UUID user_id;
    private String username;
}

