package com.fns.product.service.domain;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.dto.edit.ListImage;
import com.fns.product.service.domain.entity.*;
import com.fns.product.service.domain.event.ProductCreatedEvent;
import com.fns.product.service.domain.mapper.ProductDataMapper;
import com.fns.product.service.domain.ports.output.message.ProductMessagePublisher;
import com.fns.product.service.domain.ports.output.repository.*;
import com.fns.product.service.domain.entity.ProductImages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductPricesRepository productPricesRepository;
    private final ProductDataMapper productDataMapper;
    private final ProductBrandRepository productBrandRepository;
    private final ProductCategoriesRepository productCategoriesRepository;
    private final ProductColorsRepository productColorsRepository;
    private final ProductSizesColorBrandRepository productSizesColorBrandRepository;
    private final ProductImageRepository productImagesRepository;
    private final StockRepository stockRepository;
    private final ProductMessagePublisher productMessagePublisher;
    private final ProductDomainService productDomainService;
    private final ProductAndSizeRepository productAndSizeRepository;
    private final ProductAndColorRepository productAndColorRepository;

    public ProductCommandHandler(
            ProductRepository productRepository, ProductPricesRepository productPricesRepository,
            ProductDataMapper productDataMapper,
            ProductBrandRepository productBrandRepository,
            ProductColorsRepository productColorsRepository,
            ProductCategoriesRepository productCategoriesRepository,
            ProductSizesColorBrandRepository productSizesColorBrandRepository,
            ProductImageRepository productImagesRepository, StockRepository stockRepository, ProductMessagePublisher productMessagePublisher, ProductDomainService productDomainService, ProductAndSizeRepository productAndSizeRepository, ProductAndColorRepository productAndColorRepository
    ) {
        this.productImagesRepository = productImagesRepository;
        this.productRepository = productRepository;
        this.productPricesRepository = productPricesRepository;
        this.productDataMapper = productDataMapper;
        this.productBrandRepository = productBrandRepository;
        this.productCategoriesRepository = productCategoriesRepository;
        this.productColorsRepository = productColorsRepository;
        this.productSizesColorBrandRepository = productSizesColorBrandRepository;
        this.stockRepository = stockRepository;
        this.productMessagePublisher = productMessagePublisher;
        this.productDomainService = productDomainService;
        this.productAndSizeRepository = productAndSizeRepository;
        this.productAndColorRepository = productAndColorRepository;
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

        Stock stock = Stock.builder()
                .product_id(savedProduct.getId())
                .warehouse_id(createProductCommand.getWarehouse_id())
                .quantity(createProductCommand.getQuantity())
                .build();

        saveStock(stock);

        // Create and save the product images
        if (createProductCommand.getImage_url() != null && !createProductCommand.getImage_url().isEmpty()) {
            List<ProductImages> productImages = createProductCommand.getImage_url().stream()
                    .map(imageUrl -> ProductImages.builder()
                            .productId(savedProduct.getId())
                            .imageUrl(imageUrl)
                            .build())
                    .collect(Collectors.toList());

            saveProductImageUrl(productImages);
        }

        if(createProductCommand.getSize_id() != null && !createProductCommand.getSize_id().isEmpty()) {
            List<ProductAndSize> productAndSize = createProductCommand.getSize_id().stream()
                    .map(productAndSizeId-> ProductAndSize.builder()
                            .size_id(productAndSizeId)
                            .product_id(savedProduct.getId())
                            .build())
                    .toList();

            saveProductAndSize(productAndSize);
        }

        if(createProductCommand.getColor_id() != null && !createProductCommand.getColor_id().isEmpty()) {
            List<ProductAndColor> productAndColor = createProductCommand.getColor_id().stream()
                    .map(productAndColorId-> ProductAndColor.builder()
                            .color_id(productAndColorId)
                            .product_id(savedProduct.getId())
                            .build())
                    .toList();

            saveProductAndColor(productAndColor);
        }

        assert createProductCommand.getImage_url() != null;
        ProductCreatedEvent productEvent = productDomainService.createProduct(
                savedProduct.getId(),
                createProductCommand.getSku(),
                createProductCommand.getName(),
                createProductCommand.getSlug(),
                createProductCommand.getDescription(),
                createProductCommand.getGender(),
                createProductCommand.getPrice(),
                createProductCommand.getBrand_id(),
                createProductCommand.getProduct_categories_id(),
                createProductCommand.getSize_id().isEmpty() ? null : createProductCommand.getSize_id().get(0),
                createProductCommand.getColor_id().isEmpty() ? null : createProductCommand.getColor_id().get(0),
                createProductCommand.getImage_url().isEmpty() ? null : createProductCommand.getImage_url().get(0),
                createProductCommand.getWarehouse_id(),
                createProductCommand.getQuantity(),
                productMessagePublisher
        );

        //publish product;
        productMessagePublisher.publish(productEvent);

        return productDataMapper.createProductResponse(createProductCommand, savedProduct);
    }

    @Transactional
    public ProductResponse editProductById(UUID id, EditProductCommand editProductCommand) {
        // Retrieve the existing product
        Product existingProduct = getProduct(id);


        // Update the product fields
        existingProduct.setName(editProductCommand.getName());
        existingProduct.setSku(editProductCommand.getSku());
        existingProduct.setDescription(editProductCommand.getDescription());
        existingProduct.setSlug(editProductCommand.getSlug());
        existingProduct.setGender(editProductCommand.getGender());
        existingProduct.setBrandId(editProductCommand.getBrand_id());
        existingProduct.setProductCategoryId(editProductCommand.getProduct_categories_id());

        // Update product images
        if (editProductCommand.getImage_url() != null && !editProductCommand.getImage_url().isEmpty()) {
            // Retrieve existing images from the database
            List<ProductImages> existingImages = productImagesRepository.findByProductId(existingProduct.getId());

            log.info("existingImages: {}", existingImages);

            // Map incoming image URLs and IDs
            List<UUID> incomingImageIds = editProductCommand.getImage_url().stream()
                    .map(ListImage::getId)
                    .filter(Objects::nonNull)
                    .toList();

            List<String> incomingImageUrls = editProductCommand.getImage_url().stream()
                    .map(ListImage::getImage_url)
                    .toList();

            // Remove images not present in the incoming IDs
            List<ProductImages> imagesToRemove = existingImages.stream()
                    .filter(existingImage -> !incomingImageIds.contains(existingImage.getId()))
                    .toList();

//            productImagesRepository.deleteAll(imagesToRemove);

            // Add new images (those with no ID in the request)
//            List<ProductImages> imagesToAdd = editProductCommand.getImage_url().stream()
//                    .filter(image -> image.getId() == null)
//                    .map(image -> ProductImages.builder()
//                            .productId(existingProduct.getId())
//                            .imageUrl(image.getImageUrl())
//                            .build())
//                    .collect(Collectors.toList());
//
//                saveProductImageUrl(imagesToAdd);

            // Update existing image URLs if necessary (matching IDs)
            existingImages.forEach(existingImage -> {
                editProductCommand.getImage_url().stream()
                        .filter(image -> image.getId() != null && image.getId().equals(existingImage.getId()))
                        .findFirst()
                        .ifPresent(image -> existingImage.setImageUrl(image.getImage_url()));
            });

            log.info("existing images updated {}", existingImages);

            saveProductImageUrl(existingImages);
        }

        // Update product prices
//        if (editProductCommand.getProductPrices() != null && !editProductCommand.getProductPrices().isEmpty()) {
//            List<ProductPrices> updatedPrices = editProductCommand.getProductPrices().stream()
//                    .map(priceCommand -> productPriceMapper.priceToPriceEntity(priceCommand)) // Convert to entity
//                    .collect(Collectors.toList());
//            existingProduct.setProductPrices(updatedPrices);
//        }

        // Save updated product
        saveProduct(existingProduct);

        return productDataMapper.editProductResponse(editProductCommand, existingProduct);
    }
    public Page<ProductResponse> getAllProducts(Integer page, Integer size, String name) {
        try {
            PageRequest pageable = PageRequest.of(page, size);

            Page<Product> products = getProducts(page, size, name);

            // Map each Product to a ProductResponse
            List<ProductResponse> productResponses = products.getContent().stream()
                    .map(product -> {
                        ProductBrand brand = productBrandRepository.findById(product.getBrandId())
                                .orElseThrow(() -> new RuntimeException("Brand not found for product: " + product.getId()));

                        ProductCategories category = productCategoriesRepository.findById(product.getProductCategoryId())
                                .orElseThrow(() -> new RuntimeException("Category not found for product: " + product.getId()));

                        ProductPrices prices = productPricesRepository.getPriceByProductId(product.getId());

                        List<ProductImages> productImages  = productImagesRepository.findByProductId(product.getId());

                        List<Stock> allStock = stockRepository.findByProductId(product.getId());

                        Integer totalStock = allStock.stream()
                                .mapToInt(Stock::getQuantity)
                                .sum();

                        return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .sku(product.getSku())
                                .description(product.getDescription())
                                .slug(product.getSlug())
                                .gender(product.getGender())
                                .brand(brand)
                                .productCategory(category)
                                .stock(totalStock)
                                .imageUrl(productImages.isEmpty() ? null : productImages.get(0).getImageUrl())
                                .price(prices.getPrice())
                                .build();
                    })
                    .collect(Collectors.toList());

            // Return a new Page with the mapped content
            return new PageImpl<>(productResponses, pageable, products.getTotalElements());
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

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .slug(product.getSlug())
                .gender(product.getGender())
                .brand(brand)
                .productCategory(category)
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

    private void saveStock(Stock stock) {
        try {
            Stock savedStock = stockRepository.saveStock(stock);

            if (savedStock == null) {
                throw new RuntimeException("Failed to save stock");
            }

        } catch (Exception e) {
            log.error("Error while saving stock: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving stock", e);
        }
    }

    private void saveProductImageUrl(List<ProductImages> productImages) {
        try {
            for (ProductImages productImage : productImages) {
                ProductImages savedProductImage = productImagesRepository.saveProductImages(productImage);

                if (savedProductImage == null) {
                    throw new RuntimeException("Failed to save product image with ID: " + productImage.getId());
                }
            }

        } catch (Exception e) {
            log.error("Error while saving product images: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product images", e);
        }
    }

    private Page<Product> getProducts(Integer page, Integer size, String name) {
        return productRepository.getProducts(page, size, name);
    }

    private Product getProduct(UUID id) {
        return productRepository.getProductById(id);
    }

    private void deleteProductById(UUID id) {
        productRepository.deleteProduct(id);
    }

    private void saveProductAndSize(List<ProductAndSize> productAndSize) {
        try {
            for (ProductAndSize productAndSizeSave: productAndSize) {
                productAndSizeRepository.save(productAndSizeSave);
            }
        } catch (Exception e) {
            log.error("Error while saving product images: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product images", e);
        }
    }

    private void saveProductAndColor(List<ProductAndColor> productAndColors) {
        try {
            for (ProductAndColor productAndColorSave: productAndColors) {
                productAndColorRepository.save(productAndColorSave);
            }
        } catch (Exception e) {
            log.error("Error while saving product images: {}", e.getMessage(), e);
            throw new RuntimeException("Error while saving product images", e);
        }
    }
}
