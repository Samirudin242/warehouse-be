package com.fns.warehouse.service.domain.seeder;

import com.fns.warehouse.service.dataaccess.entity.MutationTypeEntity;
import com.fns.warehouse.service.dataaccess.repository.StockMutationTypeJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StockMutationTypeSeeder implements CommandLineRunner {

    private final StockMutationTypeJpaRepository stockMutationTypeJpaRepository;

    // Constructor with proper dependency injection
    public StockMutationTypeSeeder(StockMutationTypeJpaRepository stockMutationTypeJpaRepository) {
        this.stockMutationTypeJpaRepository = stockMutationTypeJpaRepository;
    }

    @Override
    public void run(String... args) {
        seedStockMutationTypes();
    }

    private void seedStockMutationTypes() {
        if (stockMutationTypeJpaRepository.count() == 0) {
            List<MutationTypeEntity> stockMutationTypes = Arrays.asList(
                    MutationTypeEntity.builder()
                            .name("IN")
                            .description("Stock Inbound")
                            .build(),
                    MutationTypeEntity.builder()
                            .name("OUT")
                            .description("Stock Outbound")
                            .build(),
                    MutationTypeEntity.builder()
                            .name("ADJUST")
                            .description("Stock Adjustment")
                            .build(),
                    MutationTypeEntity.builder()
                            .name("TRANSFER")
                            .description("Stock Transfer")
                            .build()
            );

            stockMutationTypeJpaRepository.saveAll(stockMutationTypes);
            System.out.println("Stock mutation types seeded successfully.");
        } else {
            System.out.println("Stock mutation types already seeded.");
        }
    }
}
