package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.entity.Cart;
import com.fns.product.service.domain.mapper.CartDataMapper;
import com.fns.product.service.domain.ports.output.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartCommandHandler {
    private final CartRepository cartRepository;
    private final CartDataMapper cartDataMapper;

    public CartCommandHandler(CartRepository cartRepository, CartDataMapper cartDataMapper) {
        this.cartRepository = cartRepository;
        this.cartDataMapper = cartDataMapper;
    }

    CreateCartResponse saveCart(CreateCartCommand createCartCommand) {
        Cart createCart = cartDataMapper.createCart(createCartCommand);

        Cart saved = createCart(createCart);

        return cartDataMapper.getCreateCartResponse(saved);
    }

    Cart createCart(Cart cart) {
        try {
            return cartRepository.saveCart(cart);
        } catch (Exception e) {
            log.error("Failed to create cart: {}", cart, e);
            throw new RuntimeException("Failed to create cart", e);
        }
    }

}
