package com.fns.product.service.dataaccess.mapper;

import com.fns.product.service.dataaccess.entity.*;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import com.fns.product.service.dataaccess.repository.StockJpaRepository;
import com.fns.product.service.dataaccess.repository.WarehouseJpaRepository;
import com.fns.product.service.domain.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CartDataAccessMapper {

    private final ProductColorsJpaRepository productColorsRepository;
    private final ProductSizeJpaRepository productSizeJpaRepository;
    private final StockJpaRepository stockJpaRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public CartDataAccessMapper(ProductColorsJpaRepository productColorsRepository, ProductSizeJpaRepository productSizeJpaRepository, StockJpaRepository stockJpaRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.productColorsRepository = productColorsRepository;
        this.productSizeJpaRepository = productSizeJpaRepository;
        this.stockJpaRepository = stockJpaRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }


    public List<Cart> getAllCart(CartEntity cartEntity, UUID userId) {
        return cartEntity.getCartItemEntities().stream()
                .map(cartItemEntity -> {
                    ProductColors productColors = productColorsRepository.findById(cartItemEntity.getSelected_color())
                            .map(ProductColorsEntity::toDomain)
                            .orElse(null);

                    ProductSizes productSizes = (ProductSizes) productSizeJpaRepository.findById(cartItemEntity.getSelected_size())
                            .map(ProductSizesEntity::toDomain)
                            .orElse(null);

                    List<StockEntity> stockEntities = stockJpaRepository.findAllByProductId(cartItemEntity.getProduct().getId());

                    Integer stockEntity = stockEntities.stream()
                            .mapToInt(StockEntity::getQuantity)
                            .sum();

                    List<UUID> warehouseIds = stockEntities.stream()
                            .map(StockEntity::getWarehouseId)
                            .distinct()
                            .collect(Collectors.toList());


                    List<WarehouseEntity> warehouseEntities = warehouseJpaRepository.findAllById(warehouseIds);

                    List<Warehouse> warehouseList = warehouseEntities.stream()
                            .map(entity -> Warehouse.builder()
                                    .id(entity.getId())
                                    .admin_id(entity.getUser() != null ? entity.getUser().getId() : null)
                                    .location_id(null)
                                    .name(entity.getName())
                                    .address(null)
                                    .city(null)
                                    .city_id(null)
                                    .province(null)
                                    .province_id(null)
                                    .postal_code(null)
                                    .latitude(null)
                                    .longitude(null)
                                    .build())
                            .toList();

                    Product product = new Product(
                            cartItemEntity.getProduct().getId(),
                            cartItemEntity.getProduct().getSku(),
                            cartItemEntity.getProduct().getName(),
                            cartItemEntity.getProduct().getSlug(),
                            cartItemEntity.getProduct().getDescription(),
                            cartItemEntity.getProduct().getGender(),
                            null,
                            cartItemEntity.getProduct().getBrand().getId(),
                            cartItemEntity.getProduct().getCategory().getId(),
                            cartItemEntity.getProduct().getImages().isEmpty()
                                    ? null
                                    : cartItemEntity.getProduct().getImages().get(0).getImage_url(),
                            null,
                            stockEntity,
                            null,
                            null,
                            warehouseList
                    );

                    return new Cart(
                            cartItemEntity.getId(),
                            productColors,
                            productSizes,
                            userId,
                            product,
                            cartItemEntity.getQuantity(),
                            cartItemEntity.getPrice()
                    );
                })
                .toList();
    }


}
