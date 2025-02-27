package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductBrandEntity;
import com.fns.product.service.dataaccess.repository.ProductBrandJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductBrandSeeder implements CommandLineRunner {

    private final ProductBrandJpaRepository productBrandJpaRepository;

    public ProductBrandSeeder(ProductBrandJpaRepository productBrandJpaRepository) {
        this.productBrandJpaRepository = productBrandJpaRepository;
    }

    @Override
    public void run(String... args) {

        if(productBrandJpaRepository.count() == 0) {
            List<Brand> brandsToSeed = List.of(
                    new Brand("Michael Kors", "michael-kors"),
                    new Brand("Gabs", "gabs"),
                    new Brand("Polo Ralph Lauren", "polo-ralph-lauren"),
                    new Brand("DKNY", "dkny"),
                    new Brand("Altea", "altea"),
                    new Brand("Moschino Love", "moschino-love"),
                    new Brand("Moncler", "moncler"),
                    new Brand("Liebeskind Berlin", "liebeskind-berlin"),
                    new Brand("Pinko", "pinko")
            );

            // Check existing brands and filter out already seeded ones
            List<String> existingSlugs = productBrandJpaRepository.findAll()
                    .stream()
                    .map(ProductBrandEntity::getSlug)
                    .toList();

            List<ProductBrandEntity> brandsToSave = brandsToSeed.stream()
                    .filter(brand -> !existingSlugs.contains(brand.slug()))
                    .map(brand -> ProductBrandEntity.builder()
                            .id(UUID.randomUUID())
                            .name(brand.name())
                            .slug(brand.slug())
                            .build())
                    .collect(Collectors.toList());

            // Save new brands if any
            if (!brandsToSave.isEmpty()) {
                productBrandJpaRepository.saveAll(brandsToSave);
            }
        }
    }

    private record Brand(String name, String slug) {}
}
