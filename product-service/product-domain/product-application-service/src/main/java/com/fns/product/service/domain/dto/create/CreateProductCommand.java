package com.fns.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@AllArgsConstructor
@Builder
public class CreateProductCommand {

    private String sku;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String slug;

    @NotNull
    private String gender;

    @NotNull
    private Integer price;

    private String imageUrl;

    @NotNull
    private UUID brand_id;

    @NotNull
    private UUID product_categories_id;

    @NotNull
    private UUID size_id;
}
