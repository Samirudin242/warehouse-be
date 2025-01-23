package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.get.CartResponse;
import com.fns.product.service.domain.ports.input.service.CartApplicationService;
import com.fns.product.service.domain.ports.output.repository.CartRepository;
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
    public CreateCartResponse saveCart(CreateCartCommand createCartCommand) {
        return cartCommandHandler.saveCart(createCartCommand);
    }

    @Override
    public List<CartResponse> getCartResponseList(UUID userId) {
        return cartCommandHandler.getCarts(userId);
    }
}
