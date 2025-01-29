package com.fns.warehouse.service.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ProductColor {
    private  UUID id;
    private String originalName;
    private String filterGroup;
}
