package com.fns.product.service.domain.ports.output.repository;

import com.fns.product.service.domain.entity.Cart;

public interface CartRepository {
    Cart saveCart(Cart cart);
}
