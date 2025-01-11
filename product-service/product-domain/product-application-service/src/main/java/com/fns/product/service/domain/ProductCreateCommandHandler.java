package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.create.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.entity.*;
import com.fns.product.service.domain.mapper.ProductDataMapper;
import com.fns.product.service.domain.ports.output.repository.*;
import com.fns.product.service.domain.entity.ProductImages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductCreateCommandHandler {

    private final ProductRepository productRepository;
    private final ProductPricesRepository productPricesRepository;
    private final ProductDataMapper productDataMapper;
    private final ProductBrandRepository productBrandRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductColorsRepository productColorsRepository;
    private final ProductSizesRepository productSizesRepository;
    private final ProductImageRepository productImagesRepository;

    public ProductCreateCommandHandler(
            ProductRepository productRepository, ProductPricesRepository productPricesRepository,
            ProductDataMapper productDataMapper,
            ProductBrandRepository productBrandRepository,
            ProductColorsRepository productColorsRepository,
            ProductCategoriesRepository productCategoriesRepository,
            ProductSizesRepository productSizesRepository,
            ProductImageRepository productImagesRepository
    ) {
        this.productImagesRepository = productImagesRepository;
        this.productRepository = productRepository;
        this.productPricesRepository = productPricesRepository;
        this.productDataMapper = productDataMapper;
        this.productBrandRepository = productBrandRepository;
        this.productCategoriesRepository = productCategoriesRepository;
        this.productColorsRepository = productColorsRepository;
        this.productSizesRepository = productSizesRepository;
    }
    @Transactional
    public ProductResponse createProduct(CreateProductCommand createProductCommand) {

        Product product = productDataMapper.createProduct(createProductCommand);

        Product savedProduct = saveProduct(product);

        // Create and save the price
        ProductPrices productPrice = ProductPrices.builder()
                .currency("USD")
                .price(createProductCommand.getPrice())
                .discountedValue(null)
                .onSales(false)
                .productId(savedProduct.getId())
                .build();

        savePricesProduct(productPrice);
        
        //Create and save the image_url
        ProductImages productImage = ProductImages.builder()
                .productId(savedProduct.getId())
                .imageUrl(createProductCommand.getImage_url().get(0))
                .build();

        saveProductImageUrl(productImage);
        

        ProductBrand brand = productBrandRepository.findById(createProductCommand.getBrand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        ProductCategories category = productCategoriesRepository.findById(createProductCommand.getProduct_categories_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductColors color = productColorsRepository.findById(createProductCommand.getColor_id())
                .orElseThrow(() -> new RuntimeException("Color not found"));

        ProductSizes size = productSizesRepository.findById(createProductCommand.getSize_id())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .sku(savedProduct.getSku())
                .description(savedProduct.getDescription())
                .slug(savedProduct.getSlug())
                .gender(savedProduct.getGender())
                .sizes(size)
                .brand(brand)
                .productCategory(category)
                .color(color)
                .build();
    }

    public ProductResponse editProductById(UUID id, EditProductCommand editProductCommand) {
        Product existingProduct = getProduct(id);

        // Update the product fields
        existingProduct.setName(editProductCommand.getName());
        existingProduct.setSku(editProductCommand.getSku());
        existingProduct.setDescription(editProductCommand.getDescription());
        existingProduct.setSlug(editProductCommand.getSlug());
        existingProduct.setGender(editProductCommand.getGender());
        existingProduct.setBrandId(editProductCommand.getBrand_id());
        existingProduct.setProductCategoryId(editProductCommand.getProduct_categories_id());
        existingProduct.setColorId(editProductCommand.getColor_id());
        existingProduct.setSizeId(editProductCommand.getSize_id());

        log.info("existing product: {}", existingProduct);
        // Save updated product
        Product updatedProduct = saveProduct(existingProduct);

        // Fetch associated details
        ProductBrand brand = productBrandRepository.findById(editProductCommand.getBrand_id())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        ProductCategories category = productCategoriesRepository.findById(editProductCommand.getProduct_categories_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        ProductColors color = productColorsRepository.findById(editProductCommand.getColor_id())
                .orElseThrow(() -> new RuntimeException("Color not found"));
        ProductSizes size = productSizesRepository.findById(editProductCommand.getSize_id())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .sku(updatedProduct.getSku())
                .description(updatedProduct.getDescription())
                .slug(updatedProduct.getSlug())
                .gender(updatedProduct.getGender())
                .sizes(size)
                .brand(brand)
                .productCategory(category)
                .color(color)
                .build();
    }

    public List<ProductResponse> getAllProducts() {
        try {
            // Fetch all products from the repository
            List<Product> products = getProducts();

            // Map each Product to a CreateProductResponse with detailed data
            return products.stream()
                    .map(product -> {
                        ProductBrand brand = productBrandRepository.findById(product.getBrandId())
                                .orElseThrow(() -> new RuntimeException("Brand not found for product: " + product.getId()));
                        ProductCategories category = productCategoriesRepository.findById(product.getProductCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found for product: " + product.getId()));
                        ProductColors color = productColorsRepository.findById(product.getColorId())
                                .orElseThrow(() -> new RuntimeException("Color not found for product: " + product.getId()));
                        ProductSizes size = productSizesRepository.findById(product.getSizeId())
                                .orElseThrow(() -> new RuntimeException("Size not found for product: " + product.getId()));

                        return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .sku(product.getSku())
                                .description(product.getDescription())
                                .slug(product.getSlug())
                                .gender(product.getGender())
                                .sizes(size)
                                .brand(brand)
                                .productCategory(category)
                                .color(color)
                                .build();
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while fetching all products: {}", e.getMessage(), e);
            throw new RuntimeException("Error while fetching all products", e);
        }
    }

    public ProductResponse getProductById(UUID id) {
        Product product = getProduct(id);

        ProductBrand brand = productBrandRepository.findById(product.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found for product: " + id));
        ProductCategories category = productCategoriesRepository.findById(product.getProductCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found for product: " + id));
        ProductColors color = productColorsRepository.findById(product.getColorId())
                .orElseThrow(() -> new RuntimeException("Color not found for product: " + id));
        ProductSizes size = productSizesRepository.findById(product.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size not found for product: " + id));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .slug(product.getSlug())
                .gender(product.getGender())
                .sizes(size)
                .brand(brand)
                .productCategory(category)
                .color(color)
                .build();
    }

    public String deleteProduct(UUID id) {
        deleteProductById(id);
        return "delete product with id: " + id + "successfully deleted";
    }

    private Product saveProduct(Product product) {
        try {
            // Attempt to save the product to the repository
            Product savedProduct = productRepository.saveProduct(product);

            if (savedProduct == null) {
                throw new RuntimeException("Failed to save product");
            }

            return savedProduct;

        } catch (Exception e) {
            log.error("Error while saving product: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product", e);
        }
    }

    private void savePricesProduct(ProductPrices productPrices) {
        try {
            // Attempt to save the product to the repository
            ProductPrices savedProductProductPrices = productPricesRepository.savePrice(productPrices);

            if (savedProductProductPrices == null) {
                throw new RuntimeException("Failed to save product");
            }

        } catch (Exception e) {
            log.error("Error while saving product: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product", e);
        }
    }

    private void saveProductImageUrl(ProductImages productImages) {
        try {
            // Attempt to save the product image to the repository
            ProductImages savedProductImages = productImagesRepository.saveProductImages(productImages);

            if (savedProductImages == null) {
                throw new RuntimeException("Failed to save product image");
            }

        } catch (Exception e) {
            log.error("Error while saving product image: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product image", e);
        }
    }

    

    private List<Product> getProducts() {
        return productRepository.getProducts();
    }

    private Product getProduct(UUID id) {
        return productRepository.getProductById(id);
    }

    private void deleteProductById(UUID id) {
        productRepository.deleteProduct(id);
    }
}
