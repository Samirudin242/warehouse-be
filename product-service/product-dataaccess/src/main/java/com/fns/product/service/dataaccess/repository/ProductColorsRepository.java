package com.fns.product.service.dataaccess.repository;

import com.fns.product.service.dataaccess.entity.ProductColors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductColorsRepository extends JpaRepository<ProductColors, UUID> {
}
