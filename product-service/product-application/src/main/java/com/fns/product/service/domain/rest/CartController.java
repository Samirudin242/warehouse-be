package com.fns.product.service.domain.rest;


import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.ports.input.service.CartApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "cart", produces = "application/vnd.api.v1+json")
public class CartController {

    private final CartApplicationService cartApplicationService;

    public CartController(CartApplicationService cartApplicationService) {
        this.cartApplicationService = cartApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateCartResponse> createCart(@RequestBody CreateCartCommand createCartCommand) {
        CreateCartResponse createCartResponse = cartApplicationService.saveCart(createCartCommand);
        log.info("Product created: {}", createCartResponse);
        return ResponseEntity.ok(createCartResponse);
    }

}
