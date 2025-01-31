package com.fns.warehouse.service.domain.dto.create;

import enitity.MutationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateMutationStock {

    private UUID from_warehouse_id;
    private UUID to_warehouse_id;
    private UUID product_id;
    private Integer quantity;
    private Date mutation_date;
    private MutationType mutation_type;
}
