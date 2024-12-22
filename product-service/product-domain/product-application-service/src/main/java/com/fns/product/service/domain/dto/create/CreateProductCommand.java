package com.fns.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Getter
@AllArgsConstructor
@Builder
public class CreateProductCommand {

    @NotNull
    private UUID id;

    private String sku;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String slug;

    private String gender;

    @NotNull
    private Double price;

    private List<String> image_url;

    @NotNull
    private UUID brand_id;

    @NotNull
    private UUID product_categories_id;

    @NotNull
    private UUID size_id;

    @NotNull
    private UUID color_id;
}
