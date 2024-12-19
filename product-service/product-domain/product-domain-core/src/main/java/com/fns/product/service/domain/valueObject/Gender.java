package com.fns.product.service.domain.valueObject;

import java.util.Objects;

public class Gender {

    private final String value;

    private static final String MEN = "men";
    private static final String WOMEN = "women";
    private static final String UNISEX = "unisex";

    // Private constructor to enforce controlled instantiation
    public Gender(String value) {
        validate(value);
        this.value = value;
    }

    // Factory method to create Gender instances
    public static Gender of(String value) {
        return new Gender(value.toLowerCase());
    }

    // Validation logic
    private void validate(String value) {
        if (!MEN.equalsIgnoreCase(value) &&
                !WOMEN.equalsIgnoreCase(value) &&
                !UNISEX.equalsIgnoreCase(value)) {
            throw new IllegalArgumentException("Invalid gender value: " + value +
                    ". Allowed values are 'men', 'women', or 'unisex'.");
        }
    }

    // Accessor for the value
    public String getValue() {
        return value;
    }

    // Equality and hashcode methods for value objects
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender = (Gender) o;
        return Objects.equals(value, gender.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    // String representation for debugging
    @Override
    public String toString() {
        return "Gender{" +
                "value='" + value + '\'' +
                '}';
    }
}
