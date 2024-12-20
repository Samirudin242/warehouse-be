package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductBrand
public class ProductBrand {
    private final UUID id;
    private String name;
    private String slug;

    // Constructor for creating a new ProductBrand instance
    public ProductBrand(String name, String slug) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (slug == null || slug.trim().isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty.");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.slug = slug;
    }

    // Private constructor for the Builder pattern
    private ProductBrand(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.name = builder.name;
        this.slug = builder.slug;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    // Setters for updatable fields
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public void setSlug(String slug) {
        if (slug == null || slug.trim().isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty.");
        }
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "ProductBrand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private String name;
        private String slug;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty.");
            }
            this.name = name;
            return this;
        }

        public Builder slug(String slug) {
            if (slug == null || slug.trim().isEmpty()) {
                throw new IllegalArgumentException("Slug cannot be null or empty.");
            }
            this.slug = slug;
            return this;
        }

        public ProductBrand build() {
            if (this.name == null || this.name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name must be set.");
            }
            return new ProductBrand(this);
        }
    }
}
