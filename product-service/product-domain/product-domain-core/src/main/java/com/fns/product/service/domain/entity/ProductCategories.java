package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductCategories
public class ProductCategories {
    private final UUID id;
    private String name;
    private String slug;
    private UUID parentId;

    // Constructor for creating a new ProductCategories instance
    public ProductCategories(String name, String slug, UUID parentId) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (slug == null || slug.trim().isEmpty()) {
            throw new IllegalArgumentException("Slug cannot be null or empty.");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
    }

    // Private constructor for the Builder pattern
    private ProductCategories(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.name = builder.name;
        this.slug = builder.slug;
        this.parentId = builder.parentId;
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

    public UUID getParentId() {
        return parentId;
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

    public void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ProductCategories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private String name;
        private String slug;
        private UUID parentId;

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

        public Builder parentId(UUID parentId) {
            this.parentId = parentId;
            return this;
        }

        public ProductCategories build() {
            if (this.name == null || this.name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name must be set.");
            }
            return new ProductCategories(this);
        }
    }
}
