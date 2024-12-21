package com.fns.product.service.domain.dto.edit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;
@Getter
@AllArgsConstructor
@Builder
@Data
public class EditProductCommand {
    @NotNull
    private String name;
    @NotNull
    private String sku;
    private String description;
    private String slug;
    private String gender;
    @NotNull
    private UUID brand_id;
    @NotNull
    private UUID product_categories_id;
    @NotNull
    private UUID color_id;
    @NotNull
    private UUID size_id;
}
