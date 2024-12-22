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
                    new Size("35", true),
                    new Size("35.5", true),
                    new Size("36", true),
                    new Size("36.5", true),
                    new Size("37", true),
                    new Size("37.5", true),
                    new Size("38", true),
                    new Size("38.5", true),
                    new Size("39", true),
                    new Size("39.5", true),
                    new Size("40", true),
                    new Size("40.5", true),
                    new Size("41", true),
                    new Size("41.5", true),
                    new Size("42", true),
                    new Size("42.5", true),
                    new Size("43", true),
                    new Size("43.5", true),
                    new Size("44", true),
                    new Size("44.5", true),
                    new Size("45", true)
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
