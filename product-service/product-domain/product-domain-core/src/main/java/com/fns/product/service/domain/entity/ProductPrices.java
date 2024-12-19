package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductPrices
public class ProductPrices {
    private final UUID id; // Unique ID for the price
    private final UUID productId; // Associated Product ID
    private String currency; // Currency code (e.g., "USD", "EUR")
    private Double price; // Base price of the product
    private Double discountedValue; // Discounted price, if applicable
    private Boolean onSales; // Flag indicating if the product is on sale

    // Constructor for creating a new ProductPrices instance
    public ProductPrices(UUID productId, String currency, Double price, Double discountedValue, Boolean onSales) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty.");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        if (discountedValue != null && discountedValue < 0) {
            throw new IllegalArgumentException("Discounted value cannot be negative.");
        }
        if (onSales == null) {
            throw new IllegalArgumentException("On sales status cannot be null.");
        }

        this.id = UUID.randomUUID();
        this.productId = productId;
        this.currency = currency;
        this.price = price;
        this.discountedValue = discountedValue != null ? discountedValue : price; // Default to price if not set
        this.onSales = onSales;
    }

    // Private constructor for the Builder pattern
    private ProductPrices(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.productId = builder.productId;
        this.currency = builder.currency;
        this.price = builder.price;
        this.discountedValue = builder.discountedValue != null ? builder.discountedValue : builder.price;
        this.onSales = builder.onSales;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getPrice() {
        return price;
    }

    public Double getDiscountedValue() {
        return discountedValue;
    }

    public Boolean getOnSales() {
        return onSales;
    }

    // Setters for updatable fields
    public void setCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty.");
        }
        this.currency = currency;
    }

    public void setPrice(Double price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        this.price = price;
    }

    public void setDiscountedValue(Double discountedValue) {
        if (discountedValue != null && discountedValue < 0) {
            throw new IllegalArgumentException("Discounted value cannot be negative.");
        }
        this.discountedValue = discountedValue;
    }

    public void setOnSales(Boolean onSales) {
        if (onSales == null) {
            throw new IllegalArgumentException("On sales status cannot be null.");
        }
        this.onSales = onSales;
    }

    @Override
    public String toString() {
        return "ProductPrices{" +
                "id=" + id +
                ", productId=" + productId +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                ", discountedValue=" + discountedValue +
                ", onSales=" + onSales +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private UUID productId;
        private String currency;
        private Double price;
        private Double discountedValue;
        private Boolean onSales;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder productId(UUID productId) {
            if (productId == null) {
                throw new IllegalArgumentException("Product ID cannot be null.");
            }
            this.productId = productId;
            return this;
        }

        public Builder currency(String currency) {
            if (currency == null || currency.trim().isEmpty()) {
                throw new IllegalArgumentException("Currency cannot be null or empty.");
            }
            this.currency = currency;
            return this;
        }

        public Builder price(Double price) {
            if (price == null || price <= 0) {
                throw new IllegalArgumentException("Price must be greater than 0.");
            }
            this.price = price;
            return this;
        }

        public Builder discountedValue(Double discountedValue) {
            if (discountedValue != null && discountedValue < 0) {
                throw new IllegalArgumentException("Discounted value cannot be negative.");
            }
            this.discountedValue = discountedValue;
            return this;
        }

        public Builder onSales(Boolean onSales) {
            if (onSales == null) {
                throw new IllegalArgumentException("On sales status cannot be null.");
            }
            this.onSales = onSales;
            return this;
        }

        public ProductPrices build() {
            if (productId == null) {
                throw new IllegalArgumentException("Product ID must be set.");
            }
            if (currency == null || currency.trim().isEmpty()) {
                throw new IllegalArgumentException("Currency must be set.");
            }
            if (price == null || price <= 0) {
                throw new IllegalArgumentException("Price must be set and greater than 0.");
            }
            return new ProductPrices(this);
        }
    }
}
