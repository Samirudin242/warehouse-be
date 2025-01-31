package com.fns.warehouse.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Data
public class CreateOrderCommand {
    private UUID user_id;
    private Date order_date;
    private Double total_amount;
    private Double total_shipping;
    private UUID warehouse_id;
    private String user_address;
    private Double latitude_user_address;
    private Double longitude_user_address;
    private String payment_method;
    private Boolean stockMutations;
    private List<CreateOrderItemCommand> orders;
}
