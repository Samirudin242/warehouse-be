package com.fns.product.service.domain.seeder;

import com.fns.product.service.dataaccess.entity.ProductCategoriesEntity;
import com.fns.product.service.dataaccess.repository.ProductCategoriesJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductCategorySeeder implements CommandLineRunner {

    private final ProductCategoriesJpaRepository productCategoriesJpaRepository;

    public ProductCategorySeeder(ProductCategoriesJpaRepository productCategoriesRep) {
        this.productCategoriesJpaRepository = productCategoriesRep;
    }

    @Override
    public void run(String... args) throws Exception {

        if(productCategoriesJpaRepository.count() == 0) {
            seedCategories();
        }

    }

    private void seedCategories() {
        // Create parent categories
        ProductCategoriesEntity womenCategory = createCategory("women", null);
        ProductCategoriesEntity menCategory = createCategory("men", null);
        ProductCategoriesEntity accessoriesCategory = createCategory("accessories", null);

        // Create subcategories for women
        ProductCategoriesEntity womenBagsCategory = createCategory("bags", womenCategory.getId());
        ProductCategoriesEntity womenClothingCategory = createCategory("clothing", womenCategory.getId());
        ProductCategoriesEntity womenShoesCategory = createCategory("shoes", womenCategory.getId());

        //Add bags sub-categories for women
        createCategory("clutches", womenBagsCategory.getId());
        createCategory("handbag", womenBagsCategory.getId());
        createCategory("shopper", womenBagsCategory.getId());
        createCategory("Shoulder bags", womenBagsCategory.getId());
        createCategory("wallets", womenBagsCategory.getId());

        // Add clothing sub-subcategories for women
        createCategory("blazer", womenClothingCategory.getId());
        createCategory("dresses", womenClothingCategory.getId());
        createCategory("jackets", womenClothingCategory.getId());
        createCategory("jeans", womenClothingCategory.getId());
        createCategory("shirts", womenClothingCategory.getId());
        createCategory("skirts", womenClothingCategory.getId());
        createCategory("t-shirts", womenClothingCategory.getId());
        createCategory("tops", womenClothingCategory.getId());
        createCategory("trouser", womenClothingCategory.getId());

        //Add shoes sub-categories for women
        createCategory("ankle boots", womenShoesCategory.getId());
        createCategory("ballerinas", womenShoesCategory.getId());
        createCategory("loafers", womenShoesCategory.getId());
        createCategory("pumps", womenShoesCategory.getId());
        createCategory("sneakers", womenShoesCategory.getId());

        // Create subcategories for men
        ProductCategoriesEntity menClothingCategory = createCategory("clothing", menCategory.getId());
        ProductCategoriesEntity menShoesCategory = createCategory("shoes", menCategory.getId());

        // Add clothing sub-subcategories for men
        createCategory("blazer", menClothingCategory.getId());
        createCategory("dresses", menClothingCategory.getId());
        createCategory("jackets", menClothingCategory.getId());
        createCategory("jeans", menClothingCategory.getId());
        createCategory("shirts", menClothingCategory.getId());
        createCategory("skirts", menClothingCategory.getId());
        createCategory("t-shirts", menClothingCategory.getId());
        createCategory("tops", menClothingCategory.getId());
        createCategory("trouser", menClothingCategory.getId());

        // Add shoes sub-subcategories for men
        createCategory("lace-up shoes", menShoesCategory.getId());
        createCategory("loafers", menShoesCategory.getId());
        createCategory("sneakers", menShoesCategory.getId());


        // Create subcategories for accessories
        ProductCategoriesEntity accessoriesMenCategory = createCategory("men", accessoriesCategory.getId());
        ProductCategoriesEntity accessoriesWomenCategory = createCategory("women", accessoriesCategory.getId());

        // Add clothing sub-subcategory for accessories (women)
        createCategory("clothing", accessoriesWomenCategory.getId());
        createCategory("looks", accessoriesWomenCategory.getId());
        createCategory("sunglasses", accessoriesWomenCategory.getId());

        // Add clothing sub-subcategory for accessories (men)
        createCategory("clothing", accessoriesMenCategory.getId());

        System.out.println("Categories seeded successfully!");
    }

    private ProductCategoriesEntity createCategory(String name, UUID parentId) {
        ProductCategoriesEntity category = ProductCategoriesEntity.builder()
                .name(name)
                .slug(name.toLowerCase().replace(" ", "-"))
                .parentId(parentId)
                .build();

        return productCategoriesJpaRepository.save(category);
    }
}
