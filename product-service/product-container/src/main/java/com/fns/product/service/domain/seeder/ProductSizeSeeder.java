package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductSizes;
import com.fns.product.service.dataaccess.repository.ProductSizeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductSizeSeeder implements CommandLineRunner {

    private final ProductSizeRepository productSizeRepository;

    public ProductSizeSeeder(ProductSizeRepository productSizeRepository) {
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public void run(String... args) {
        if (productSizeRepository.count() == 0) {
            List<Size> sizes = List.of(
                    new Size("S", true),
                    new Size("M", true),
                    new Size("L", true),
                    new Size("XL", true),
                    new Size("XXL", true),
                    new Size("XXXL", true)
            );

            List<ProductSizes> sizesToSave = sizes.stream()
                    .map(s -> ProductSizes.builder()
                            .id(UUID.randomUUID())
                            .size(s.size())
                            .is_stock(s.isStock())
                            .build())
                    .collect(Collectors.toList());

            if (!sizesToSave.isEmpty()) {
                productSizeRepository.saveAll(sizesToSave);
            }
        }
    }

    // Record class for size data
    private record Size(String size, Boolean isStock) {}
}
