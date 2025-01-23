package com.fns.product.service.domain.mapper;

import com.fns.product.service.domain.dto.create.CreateCartCommand;
import com.fns.product.service.domain.dto.create.CreateCartResponse;
import com.fns.product.service.domain.dto.get.CartResponse;
import com.fns.product.service.domain.entity.Cart;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.entity.ProductColors;
import com.fns.product.service.domain.entity.ProductSizes;
import com.fns.product.service.domain.ports.output.repository.ProductColorsRepository;
import com.fns.product.service.domain.ports.output.repository.ProductRepository;
import com.fns.product.service.domain.ports.output.repository.ProductSizesColorBrandRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartDataMapper {

    private final ProductRepository productRepository;
    private final ProductColorsRepository productColorsRepository;
    private final ProductSizesColorBrandRepository productSizesColorBrandRepository;

    public CartDataMapper(ProductRepository productRepository, ProductColorsRepository productColorsRepository, ProductSizesColorBrandRepository productSizesColorBrandRepository) {
        this.productRepository = productRepository;
        this.productColorsRepository = productColorsRepository;
        this.productSizesColorBrandRepository = productSizesColorBrandRepository;
    }

    public Cart createCart(CreateCartCommand createCartCommand) {

        Product product = productRepository.getProductById(createCartCommand.getProduct_id());
        ProductSizes size = productSizesColorBrandRepository.findById(createCartCommand.getSelected_size())
                .orElseThrow(() -> new RuntimeException("Size not found"));
        ProductColors color = productColorsRepository.findById(createCartCommand.getSelected_color())
                .orElseThrow(() -> new RuntimeException("color not found"));

        return Cart
                .builder()
                .selected_color(color)
                .selected_size(size)
                .quantity(createCartCommand.getQuantity())
                .product(product)
                .user_id(createCartCommand.getUser_id())
                .price(createCartCommand.getPrice())
                .build();
    }

    public CreateCartResponse getCreateCartResponse(Cart cart) {

        return CreateCartResponse
                .builder()
                .id(cart.getId())
                .selected_color(cart.getSelected_color().getId())
                .selected_size(cart.getSelected_size().getId())
                .quantity(cart.getQuantity())
                .product(cart.getProduct())
                .user_id(cart.getUser_id())
                .price(cart.getPrice())
                .build();
    }

    public List<CartResponse> getAllCart(List<Cart> carts) {
        return carts.stream()
                .map(cart -> CartResponse.builder()
                        .id(cart.getId())
                        .colors(cart.getSelected_color())
                        .size(cart.getSelected_size())
                        .product(cart.getProduct())
                        .quantity(cart.getQuantity())
                        .price(cart.getPrice())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
