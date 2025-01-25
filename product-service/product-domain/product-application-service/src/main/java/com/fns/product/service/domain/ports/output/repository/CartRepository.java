package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Cart;

import java.util.List;
import java.util.UUID;

public interface CartRepository {
    Cart saveCart(Cart cart);

    Cart editCart(Cart cart, UUID id);

    List<Cart> getAllCart(UUID userId);
}
