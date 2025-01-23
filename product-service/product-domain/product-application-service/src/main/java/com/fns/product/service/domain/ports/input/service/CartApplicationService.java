package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.get.CartResponse;
import com.fns.product.service.domain.entity.Cart;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CartApplicationService {
    CreateCartResponse saveCart(@Valid CreateCartCommand createCartCommand);

    List<CartResponse> getCartResponseList(UUID userId);

}
