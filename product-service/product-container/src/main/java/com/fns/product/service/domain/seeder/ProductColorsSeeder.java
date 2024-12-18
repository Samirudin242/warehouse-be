package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductColors;
import com.fns.product.service.dataaccess.repository.ProductColorsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ProductColorsSeeder implements CommandLineRunner {

    private final ProductColorsRepository productColorsRepository;

    public ProductColorsSeeder(ProductColorsRepository productColorsRepository) {
        this.productColorsRepository = productColorsRepository;
    }

    @Override
    public void run(String... args) {
        if (productColorsRepository.count() == 0) {
            ProductColors color1 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("white")
                    .filter_group("white")
                    .build();

            ProductColors color2 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("silver")
                    .filter_group("silver")
                    .build();

            ProductColors color3 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("pink")
                    .filter_group("pink")
                    .build();

            ProductColors color4 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("beige")
                    .filter_group("beige")
                    .build();

            ProductColors color5 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("multi")
                    .filter_group("multi")
                    .build();

            ProductColors color6 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("black")
                    .filter_group("black")
                    .build();

            ProductColors color7 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("brow")
                    .filter_group("brow")
                    .build();

            ProductColors color8 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("grey")
                    .filter_group("grey")
                    .build();

            ProductColors color9 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("pink")
                    .filter_group("pink")
                    .build();

            ProductColors color10 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("olive")
                    .filter_group("olive")
                    .build();

            ProductColors color11 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("orange")
                    .filter_group("orange")
                    .build();

            ProductColors color12 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("gold")
                    .filter_group("gold")
                    .build();

            ProductColors color13 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("yellow")
                    .filter_group("yellow")
                    .build();

            ProductColors color14 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("red")
                    .filter_group("red")
                    .build();

            ProductColors color15 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("green")
                    .filter_group("green")
                    .build();

            ProductColors color16 = ProductColors.builder()
                    .id(UUID.randomUUID())
                    .original_name("blue")
                    .filter_group("blue")
                    .build();

            productColorsRepository.save(color1);
            productColorsRepository.save(color2);
            productColorsRepository.save(color3);
            productColorsRepository.save(color4);
            productColorsRepository.save(color5);
            productColorsRepository.save(color6);
            productColorsRepository.save(color7);
            productColorsRepository.save(color8);
            productColorsRepository.save(color9);
            productColorsRepository.save(color10);
            productColorsRepository.save(color11);
            productColorsRepository.save(color12);
            productColorsRepository.save(color13);
            productColorsRepository.save(color14);
            productColorsRepository.save(color15);
            productColorsRepository.save(color16);

            System.out.println("Seeded ProductColors table with initial data.");
        } else {
            System.out.println("ProductColors table already has data. Seeder skipped.");
        }
    }
}
