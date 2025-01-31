package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.CartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.delete.DeleteCartItemResponse;
import com.fns.product.service.domain.dto.get.CartResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface CartApplicationService {
    CreateCartResponse saveCart(@Valid CartCommand cartCommand);

    List<CartResponse> getCartResponseList(UUID userId);

    CreateCartResponse editCart(@Valid CartCommand cartCommand, UUID id);

    DeleteCartItemResponse deleteCart(UUID cartItemId);

}
