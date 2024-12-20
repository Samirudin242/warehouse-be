package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductSizesEntity;
import com.fns.product.service.dataaccess.repository.ProductSizeJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductSizeSeeder implements CommandLineRunner {

    private final ProductSizeJpaRepository productSizeJpaRepository;

    public ProductSizeSeeder(ProductSizeJpaRepository productSizeJpaRepository) {
        this.productSizeJpaRepository = productSizeJpaRepository;
    }

    @Override
    public void run(String... args) {
        if (productSizeJpaRepository.count() == 0) {
            List<Size> sizes = List.of(
                    new Size("S", true),
                    new Size("M", true),
                    new Size("L", true),
                    new Size("XL", true),
                    new Size("XXL", true),
                    new Size("XXXL", true)
            );

            List<ProductSizesEntity> sizesToSave = sizes.stream()
                    .map(s -> ProductSizesEntity.builder()
                            .id(UUID.randomUUID())
                            .size(s.size())
                            .isStock(s.isStock())
                            .build())
                    .collect(Collectors.toList());

            if (!sizesToSave.isEmpty()) {
                productSizeJpaRepository.saveAll(sizesToSave);
            }
        }
    }

    // Record class for size data
    private record Size(String size, Boolean isStock) {}
}
