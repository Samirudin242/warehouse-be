package com.fns.warehouse.service.domain.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Setter
@Getter
@Builder
public class CreateOrderResponse {
    private UUID order_id;
    private Date order_date;
    private UUID user_id;
}
