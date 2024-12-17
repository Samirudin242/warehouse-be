package com.fns.product.service.domain.entity;


import com.fns.product.service.domain.valueObject.Category;
import com.fns.product.service.domain.valueObject.Price;

import java.util.UUID;

// Aggregate Root: Product
public class Product {
    private final UUID id;
    private String name;
    private Price price;
    private Category category;
    private int stock;
    private String imageUrl;

    public Product(String name, Price price, Category category, int stock, String imageUrl) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    // Getter
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // Setter untuk Update
    public void updateDetails(String name, Price price, Category category, int stock, String imageUrl) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public boolean isAvailable() {
        return stock > 0;
    }
}

