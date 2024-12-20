package com.fns.product.service.domain.entity;

import java.util.UUID;

// Entity: ProductColors
public class ProductColors {
    private final UUID id;
    private String originalName;
    private String filterGroup;

    // Constructor for creating a new ProductColors instance
    public ProductColors(String originalName, String filterGroup) {
        if (originalName == null || originalName.trim().isEmpty()) {
            throw new IllegalArgumentException("Original name cannot be null or empty.");
        }
        if (filterGroup == null || filterGroup.trim().isEmpty()) {
            throw new IllegalArgumentException("Filter group cannot be null or empty.");
        }

        this.id = UUID.randomUUID();
        this.originalName = originalName;
        this.filterGroup = filterGroup;
    }

    // Private constructor for the Builder pattern
    private ProductColors(Builder builder) {
        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.originalName = builder.originalName;
        this.filterGroup = builder.filterGroup;
    }

    // Static factory method for the Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getFilterGroup() {
        return filterGroup;
    }

    // Setters for updatable fields
    public void setOriginalName(String originalName) {
        if (originalName == null || originalName.trim().isEmpty()) {
            throw new IllegalArgumentException("Original name cannot be null or empty.");
        }
        this.originalName = originalName;
    }

    public void setFilterGroup(String filterGroup) {
        if (filterGroup == null || filterGroup.trim().isEmpty()) {
            throw new IllegalArgumentException("Filter group cannot be null or empty.");
        }
        this.filterGroup = filterGroup;
    }

    @Override
    public String toString() {
        return "ProductColors{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", filterGroup='" + filterGroup + '\'' +
                '}';
    }

    // Builder Class
    public static class Builder {
        private UUID id;
        private String originalName;
        private String filterGroup;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder originalName(String originalName) {
            if (originalName == null || originalName.trim().isEmpty()) {
                throw new IllegalArgumentException("Original name cannot be null or empty.");
            }
            this.originalName = originalName;
            return this;
        }

        public Builder filterGroup(String filterGroup) {
            if (filterGroup == null || filterGroup.trim().isEmpty()) {
                throw new IllegalArgumentException("Filter group cannot be null or empty.");
            }
            this.filterGroup = filterGroup;
            return this;
        }

        public ProductColors build() {
            if (this.originalName == null || this.originalName.trim().isEmpty()) {
                throw new IllegalArgumentException("Original name must be set.");
            }
            return new ProductColors(this);
        }
    }
}
