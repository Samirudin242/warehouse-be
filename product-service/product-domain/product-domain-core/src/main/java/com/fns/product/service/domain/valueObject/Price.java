package com.fns.product.service.domain.valueObject;

import java.util.Objects;

// Value Object untuk Harga Produk
public class Price {
    private final Double value;

    public Price(Double value) {
        if (value == null) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value.equals(price.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}