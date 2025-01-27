package com.fns.warehouse.service.domain.dto.create;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class NearestWarehouseCommand {
    private UserCommand user;
    private List<UUID> warehouse;
}

