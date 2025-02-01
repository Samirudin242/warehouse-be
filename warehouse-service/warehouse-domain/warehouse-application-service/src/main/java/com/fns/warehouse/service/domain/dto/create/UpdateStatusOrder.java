package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UpdateStatusOrder {
    private UUID order_id;
    private Date order_date;
}
