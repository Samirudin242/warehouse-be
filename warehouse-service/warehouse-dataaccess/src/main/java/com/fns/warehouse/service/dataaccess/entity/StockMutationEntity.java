package com.fns.warehouse.service.dataaccess.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    private UUID from_warehouse_id;

    private UUID to_warehouse_id;

    private UUID product_id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "mutation_type_id", referencedColumnName = "id")
    private MutationTypeEntity mutationType;
}
