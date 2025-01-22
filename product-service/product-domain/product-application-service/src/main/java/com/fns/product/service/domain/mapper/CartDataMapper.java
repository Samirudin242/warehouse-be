package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartDataMapper {
    public Cart createCart(CreateCartCommand createCartCommand) {
        return Cart
                .builder()
                .selected_color(createCartCommand.getSelected_color())
                .selected_size(createCartCommand.getSelected_size())
                .quantity(createCartCommand.getQuantity())
                .product_id(createCartCommand.getProduct_id())
                .user_id(createCartCommand.getUser_id())
                .price(createCartCommand.getPrice())
                .build();
    }

    public CreateCartResponse getCreateCartResponse(Cart cart) {
        return CreateCartResponse
                .builder()
                .id(cart.getId())
                .selected_color(cart.getSelected_color())
                .selected_size(cart.getSelected_size())
                .quantity(cart.getQuantity())
                .product_id(cart.getProduct_id())
                .user_id(cart.getUser_id())
                .price(cart.getPrice())
                .build();
    }
}
