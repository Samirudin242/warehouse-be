package com.fns.product.service.domain.valueObject;

import java.math.BigDecimal;
import java.util.Objects;

// Value Object untuk Harga Produk
public class Price {
    private final BigDecimal value;

    public Price(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
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