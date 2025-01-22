package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.CartEntity;
import com.fns.product.service.dataaccess.entity.CartItemEntity;
import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.dataaccess.repository.CartItemJpaRepository;
import com.fns.product.service.dataaccess.repository.CartJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.entity.Cart;
import com.fns.product.service.domain.ports.output.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
public class CartServiceImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartItemJpaRepository cartItemJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public CartServiceImpl(CartJpaRepository cartJpaRepository, CartItemJpaRepository cartItemJpaRepository, UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    @Transactional
    public Cart saveCart(Cart cart) {
        Optional<CartEntity> cartEntityOptional = cartJpaRepository.findByUsersId(cart.getUser_id());
        CartEntity cartEntity;

        if (cartEntityOptional.isPresent()) {
            cartEntity = cartEntityOptional.get();
        } else {
            UserEntity userEntity = userJpaRepository.findById(cart.getUser_id())
                    .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + cart.getUser_id()));

            cartEntity = CartEntity.builder()
                    .users(userEntity)
                    .total_price(0)
                    .build();

            cartEntity = cartJpaRepository.save(cartEntity);
        }

        ProductEntity productEntity = productJpaRepository.findById(cart.getProduct_id())
                .orElseThrow(() -> new IllegalArgumentException("Product not found for id: " + cart.getProduct_id()));

        Optional<CartItemEntity> existingCartItem = cartItemJpaRepository.findByCartIdAndProductId(cartEntity.getId(), cart.getProduct_id());

        log.info("Found existing cart, {}", existingCartItem);

        if (existingCartItem.isPresent()) {
            CartItemEntity cartItemEntity = existingCartItem.get();
            int newQuantity = cartItemEntity.getQuantity() + cart.getQuantity();
            cartItemEntity.setQuantity(newQuantity);

            int newPrice = newQuantity * cart.getPrice().intValue();
            cartItemEntity.setPrice(newPrice);

            if (cartItemEntity.getStatus() == null) {
                cartItemEntity.setStatus(CartItemEntity.CartStatus.ACTIVE);
            }

            cartItemJpaRepository.save(cartItemEntity);

            int updatedTotalPrice = cartEntity.getTotal_price() + (cart.getQuantity() * cart.getPrice().intValue());
            cartEntity.setTotal_price(updatedTotalPrice);
        } else {
            CartItemEntity cartItemEntity = CartItemEntity.builder()
                    .selected_color(cart.getSelected_color())
                    .selected_size(cart.getSelected_size())
                    .quantity(cart.getQuantity())
                    .price(cart.getPrice().intValue())
                    .product(productEntity)
                    .cart(cartEntity)
                    .status(CartItemEntity.CartStatus.ACTIVE)
                    .build();

            cartItemJpaRepository.save(cartItemEntity);

            // Update the total price of the cart
            int updatedTotalPrice = cartEntity.getTotal_price() + (cart.getQuantity() * cart.getPrice().intValue());
            cartEntity.setTotal_price(updatedTotalPrice);
        }

        // Save the updated cart
        cartJpaRepository.save(cartEntity);

        return new Cart(
                cartEntity.getId(),
                cart.getSelected_color(),
                cart.getSelected_size(),
                cart.getUser_id(),
                cart.getProduct_id(),
                cart.getQuantity(),
                (double) cartEntity.getTotal_price()
        );
    }

}
