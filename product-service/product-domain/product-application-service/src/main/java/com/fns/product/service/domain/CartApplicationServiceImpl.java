package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.ports.input.service.CartApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Service
public class CartApplicationServiceImpl implements CartApplicationService {

    private final CartCommandHandler cartCommandHandler;

    public CartApplicationServiceImpl(CartCommandHandler cartCommandHandler) {
        this.cartCommandHandler = cartCommandHandler;
    }

    @Override
    public CreateCartResponse saveCart(CartCommand cartCommand) {
        return cartCommandHandler.saveCart(cartCommand);
    }

    @Override
    public List<com.fns.product.service.domain.dto.get.CartResponse> getCartResponseList(UUID userId) {
        return cartCommandHandler.getCarts(userId);
    }

    @Override
    public CreateCartResponse editCart(CartCommand cartCommand, UUID id) {
        return cartCommandHandler.editCart(cartCommand, id);
    }
}
