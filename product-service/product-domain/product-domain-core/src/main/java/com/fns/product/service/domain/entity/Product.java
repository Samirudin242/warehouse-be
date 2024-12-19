package com.fns.product.service.domain.entity;

import com.fns.product.service.domain.valueObject.Category;
import com.fns.product.service.domain.valueObject.Gender;
import com.fns.product.service.domain.valueObject.Price;

import java.util.UUID;

// Aggregate Root: Product
public class Product {

    private final UUID id;
    private String sku;
    private String name;
    private String slug;
    private String description;
    private Gender gender;
    private Price price;
    private UUID brandId;
    private UUID productCategoryId;
    private UUID sizeId;
    private String imageUrl;

    // Private constructor for builder
    private Product(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.sku = builder.sku;
        this.name = builder.name;
        this.slug = generateSlug(builder.name);
        this.gender = builder.gender;
        this.price = builder.price;
        this.description = builder.description;
        this.brandId = builder.brandId;
        this.productCategoryId = builder.productCategoryId;
        this.sizeId = builder.sizeId;
        this.imageUrl = builder.imageUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public Gender getGender() {
        return gender;
    }

    public Price getPrice() {
        return price;
    }

    public UUID getBrandId() {
        return brandId;
    }

    public UUID getProductCategoryId() {
        return productCategoryId;
    }

    public UUID getSizeId() {
        return sizeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setters for updatable fields
    public void updateDetails(String name, Price price, Gender gender, String imageUrl) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            this.slug = generateSlug(name);
        }
        if (price != null) {
            this.price = price;
        }
        if (gender != null) {
            this.gender = gender;
        }
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setBrandId(UUID brandId) {
        if (brandId == null) {
            throw new IllegalArgumentException("Brand ID cannot be null.");
        }
        this.brandId = brandId;
    }

    public void setProductCategoryId(UUID productCategoryId) {
        if (productCategoryId == null) {
            throw new IllegalArgumentException("Product category ID cannot be null.");
        }
        this.productCategoryId = productCategoryId;
    }

    public void setSizeId(UUID sizeId) {
        if (sizeId == null) {
            throw new IllegalArgumentException("Size ID cannot be null.");
        }
        this.sizeId = sizeId;
    }

    private String generateSlug(String name) {
        return name.toLowerCase().replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", gender=" + gender +
                ", price=" + price +
                ", brandId=" + brandId +
                ", productCategoryId=" + productCategoryId +
                ", sizeId=" + sizeId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    // Builder class
    public static class Builder {
        private UUID id;
        private String sku;
        private String name;
        private String slug;
        private String description;
        private Gender gender;
        private Price price;
        private UUID brandId;
        private UUID productCategoryId;
        private UUID sizeId;
        private String imageUrl;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder slug(String slug) {
            this.slug = slug;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty.");
            }
            this.name = name;
            return this;
        }

        public Builder gender(Gender gender) {
            if (gender == null) {
                throw new IllegalArgumentException("Product gender cannot be null.");
            }
            this.gender = gender;
            return this;
        }

        public Builder price(Price price) {
            if (price == null) {
                throw new IllegalArgumentException("Product price cannot be null.");
            }
            this.price = price;
            return this;
        }

        public Builder brandId(UUID brandId) {
            if (brandId == null) {
                throw new IllegalArgumentException("Brand ID cannot be null.");
            }
            this.brandId = brandId;
            return this;
        }

        public Builder productCategoryId(UUID productCategoryId) {
            if (productCategoryId == null) {
                throw new IllegalArgumentException("Product category ID cannot be null.");
            }
            this.productCategoryId = productCategoryId;
            return this;
        }

        public Builder sizeId(UUID sizeId) {
            if (sizeId == null) {
                throw new IllegalArgumentException("Size ID cannot be null.");
            }
            this.sizeId = sizeId;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
