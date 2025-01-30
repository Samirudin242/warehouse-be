package com.fns.product.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class CartCommand {

    @NotNull
    private UUID user_id;

    @NotNull
    private UUID product_id;

    @NotNull
    private UUID selected_color;

    @NotNull
    private UUID selected_size;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer price;
}
