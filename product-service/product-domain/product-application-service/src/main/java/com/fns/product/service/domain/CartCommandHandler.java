package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.delete.DeleteCartItemResponse;
import com.fns.product.service.domain.dto.get.CartResponse;
import com.fns.product.service.domain.entity.Cart;
import com.fns.product.service.domain.mapper.CartDataMapper;
import com.fns.product.service.domain.ports.output.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class CartCommandHandler {
    private final CartRepository cartRepository;
    private final CartDataMapper cartDataMapper;

    public CartCommandHandler(CartRepository cartRepository, CartDataMapper cartDataMapper) {
        this.cartRepository = cartRepository;
        this.cartDataMapper = cartDataMapper;
    }

    CreateCartResponse saveCart(CartCommand cartCommand) {
        Cart createCart = cartDataMapper.createCart(cartCommand);

        Cart saved = createCart(createCart);

        return cartDataMapper.getCreateCartResponse(saved);
    }

    CreateCartResponse editCart(CartCommand cartCommand, UUID id) {
        Cart cart = cartDataMapper.createCart(cartCommand);

        Cart edited = updateCart(cart, id);

        return cartDataMapper.getCreateCartResponse(edited);
    }

    List<CartResponse> getCarts(UUID userId) {
        List<Cart> carts = getAllCart(userId);

        return cartDataMapper.getAllCart(carts);
    }

    DeleteCartItemResponse deleteCart(UUID cartItemId) {
        try {
            return cartRepository.deleteCartItem(cartItemId);
        } catch (Exception e) {
            log.error("Failed to delete cart: {}", cartItemId, e);
            throw new RuntimeException("Failed to delete cart", e);
        }
    }

    Cart createCart(Cart cart) {
        try {
            return cartRepository.saveCart(cart);
        } catch (Exception e) {
            log.error("Failed to create cart: {}", cart, e);
            throw new RuntimeException("Failed to create cart", e);
        }
    }
    Cart updateCart(Cart cart, UUID id) {
        try {
            return cartRepository.editCart(cart, id);
        } catch (Exception e) {
            log.error("Failed to create cart: {}", cart, e);
            throw new RuntimeException("Failed to create cart", e);
        }
    }


    List<Cart> getAllCart(UUID userId) {
        try {
            return cartRepository.getAllCart(userId);
        } catch (Exception e) {
            log.error("Failed to get all carts: {}", userId, e);
            throw new RuntimeException("Failed to get all carts", e);
        }
    }

}
