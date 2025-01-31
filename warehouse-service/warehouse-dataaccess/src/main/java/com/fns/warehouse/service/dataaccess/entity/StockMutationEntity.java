package com.fns.warehouse.service.dataaccess.entity;


import enitity.MutationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_mutations")
@Entity
public class StockMutationEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private UUID from_warehouse_id;

    private UUID to_warehouse_id;

    private UUID product_id;

    private Integer quantity;

    private Date mutation_date;

    private MutationType mutation_type;
}
