package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductColorsEntity;
import com.fns.product.service.dataaccess.repository.ProductColorsJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ProductColorsSeeder implements CommandLineRunner {

    private final ProductColorsJpaRepository productColorsJpaRepository;

    public ProductColorsSeeder(ProductColorsJpaRepository productColorsJpaRepository) {
        this.productColorsJpaRepository = productColorsJpaRepository;
    }

    @Override
    public void run(String... args) {
        if (productColorsJpaRepository.count() == 0) {
            ProductColorsEntity color1 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("white")
                    .filterGroup("white")
                    .build();

            ProductColorsEntity color2 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("silver")
                    .filterGroup("silver")
                    .build();

            ProductColorsEntity color3 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("pink")
                    .filterGroup("pink")
                    .build();

            ProductColorsEntity color4 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("beige")
                    .filterGroup("beige")
                    .build();

            ProductColorsEntity color5 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("multi")
                    .filterGroup("multi")
                    .build();

            ProductColorsEntity color6 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("black")
                    .filterGroup("black")
                    .build();

            ProductColorsEntity color7 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("brow")
                    .filterGroup("brow")
                    .build();

            ProductColorsEntity color8 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("grey")
                    .filterGroup("grey")
                    .build();

            ProductColorsEntity color9 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("pink")
                    .filterGroup("pink")
                    .build();

            ProductColorsEntity color10 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("olive")
                    .filterGroup("olive")
                    .build();

            ProductColorsEntity color11 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("orange")
                    .filterGroup("orange")
                    .build();

            ProductColorsEntity color12 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("gold")
                    .filterGroup("gold")
                    .build();

            ProductColorsEntity color13 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("yellow")
                    .filterGroup("yellow")
                    .build();

            ProductColorsEntity color14 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("red")
                    .filterGroup("red")
                    .build();

            ProductColorsEntity color15 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("green")
                    .filterGroup("green")
                    .build();

            ProductColorsEntity color16 = ProductColorsEntity.builder()
                    .id(UUID.randomUUID())
                    .originalName("blue")
                    .filterGroup("blue")
                    .build();

            productColorsJpaRepository.save(color1);
            productColorsJpaRepository.save(color2);
            productColorsJpaRepository.save(color3);
            productColorsJpaRepository.save(color4);
            productColorsJpaRepository.save(color5);
            productColorsJpaRepository.save(color6);
            productColorsJpaRepository.save(color7);
            productColorsJpaRepository.save(color8);
            productColorsJpaRepository.save(color9);
            productColorsJpaRepository.save(color10);
            productColorsJpaRepository.save(color11);
            productColorsJpaRepository.save(color12);
            productColorsJpaRepository.save(color13);
            productColorsJpaRepository.save(color14);
            productColorsJpaRepository.save(color15);
            productColorsJpaRepository.save(color16);

            System.out.println("Seeded ProductColors table with initial data.");
        } else {
            System.out.println("ProductColors table already has data. Seeder skipped.");
        }
    }
}
