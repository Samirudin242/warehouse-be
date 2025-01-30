package com.fns.product.service.dataaccess.adapter;

import com.fns.product.service.dataaccess.entity.CartEntity;
import com.fns.product.service.dataaccess.entity.CartItemEntity;
import com.fns.product.service.dataaccess.entity.ProductEntity;
import com.fns.product.service.dataaccess.entity.UserEntity;
import com.fns.product.service.dataaccess.mapper.CartDataAccessMapper;
import com.fns.product.service.dataaccess.repository.CartItemJpaRepository;
import com.fns.product.service.dataaccess.repository.CartJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductJpaRepository;
import com.fns.product.service.dataaccess.repository.UserJpaRepository;
import com.fns.product.service.domain.dto.delete.DeleteCartItemResponse;
import com.fns.product.service.domain.entity.Cart;
import com.fns.product.service.domain.entity.Product;
import com.fns.product.service.domain.ports.output.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class CartServiceImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;
    private final CartItemJpaRepository cartItemJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CartDataAccessMapper cartDataAccessMapper;

    public CartServiceImpl(CartJpaRepository cartJpaRepository, CartItemJpaRepository cartItemJpaRepository, UserJpaRepository userJpaRepository, ProductJpaRepository productJpaRepository, CartDataAccessMapper cartDataAccessMapper) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartItemJpaRepository = cartItemJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.cartDataAccessMapper = cartDataAccessMapper;
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

        ProductEntity productEntity = productJpaRepository.findById(cart.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found for id: " + cart.getProduct().getId()));

        Optional<CartItemEntity> existingCartItem = cartItemJpaRepository.findByCart_IdAndProduct_Id(cartEntity.getId(), cart.getProduct().getId());


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
                        .selected_color(cart.getSelected_color().getId())
                        .selected_size(cart.getSelected_size().getId())
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
                cart.getProduct(),
                cart.getQuantity(),
                cartEntity.getTotal_price()
        );
    }

    @Override
    @Transactional
    public Cart editCart(Cart cart, UUID id) {
        CartItemEntity existingCartItemEntityOptional = cartItemJpaRepository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("cart item not found for id: " + cart.getId()));


        CartEntity cartEntityOptional = cartJpaRepository.findById(existingCartItemEntityOptional.getCart().getId())
                                    .orElseThrow(() -> new IllegalArgumentException("cart not found for id: " + existingCartItemEntityOptional.getCart().getId()));

        int updatedTotalPrice = cartEntityOptional.getTotal_price() - existingCartItemEntityOptional.getPrice() + (cart.getPrice());
        cartEntityOptional.setTotal_price(updatedTotalPrice);

        cartJpaRepository.save(cartEntityOptional);

        existingCartItemEntityOptional.setQuantity(cart.getQuantity());
        existingCartItemEntityOptional.setPrice(cart.getPrice());
        existingCartItemEntityOptional.setStatus(existingCartItemEntityOptional.getStatus());
        existingCartItemEntityOptional.setSelected_color(existingCartItemEntityOptional.getSelected_color());
        existingCartItemEntityOptional.setSelected_size(existingCartItemEntityOptional.getSelected_size());
        existingCartItemEntityOptional.setProduct(existingCartItemEntityOptional.getProduct());
        existingCartItemEntityOptional.setCart(existingCartItemEntityOptional.getCart());

        // Save the updated entity back to the database
        CartItemEntity updatedCartEntity = cartItemJpaRepository.save(existingCartItemEntityOptional);

        return new Cart(
                updatedCartEntity.getId(),
                cart.getSelected_color(),
                cart.getSelected_size(),
                cart.getUser_id(),
                cart.getProduct(),
                cart.getQuantity(),
                cartEntityOptional.getTotal_price()
        );
    }

    @Override
    @Transactional
    public List<Cart> getAllCart(UUID userId) {
        // Attempt to retrieve the cart entity
        Optional<CartEntity> optionalCartEntity = cartJpaRepository.findByUsersId(userId);

        if (optionalCartEntity.isEmpty()) {
            return new ArrayList<>();
        }

        CartEntity cartEntity = optionalCartEntity.get();
        return cartDataAccessMapper.getAllCart(cartEntity, userId);
    }

    @Override
    @Transactional
    public DeleteCartItemResponse deleteCartItem(UUID cartItemId) {
        CartItemEntity existingCartItemEntityOptional = cartItemJpaRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("cart item not found for id: " + cartItemId));

        CartEntity cartEntityOptional = cartJpaRepository.findById(existingCartItemEntityOptional.getCart().getId())
                .orElseThrow(() -> new IllegalArgumentException("cart not found for id: " + existingCartItemEntityOptional.getCart().getId()));


        int updatedTotalPrice = cartEntityOptional.getTotal_price() - existingCartItemEntityOptional.getPrice();
        cartEntityOptional.setTotal_price(updatedTotalPrice);

        cartJpaRepository.save(cartEntityOptional);

        cartItemJpaRepository.deleteById(cartItemId);

        return DeleteCartItemResponse.builder()
                .cartItemId(cartItemId)
                .message("Successfully deleted cart item with id " + cartItemId)
                .build();
    }


}
