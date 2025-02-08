package com.fns.product.service.domain.ports.input.service;

import com.fns.product.service.domain.dto.create.CreateProductCommand;
import com.fns.product.service.domain.dto.get.ProductCategoryResponse;
import com.fns.product.service.domain.dto.get.ProductResponse;
import com.fns.product.service.domain.dto.edit.EditProductCommand;
import com.fns.product.service.domain.dto.get.ProductSizeBrandColorResponse;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ProductApplicationService {
    ProductResponse createProduct (@Valid CreateProductCommand createProductCommand);

    Page<ProductResponse> getProducts(Integer page, Integer size, String name, List<UUID> categoryIds, Double minPrice, Double maxPrice);

    ProductResponse getProductById(UUID id);

    ProductResponse editProductById(UUID id, EditProductCommand editProductCommand);

    String deleteProductById(UUID id);

    List<ProductSizeBrandColorResponse> getProductSize();

    List<ProductCategoryResponse> getProductCategory();

    List<ProductSizeBrandColorResponse> getProductBrand();

    List<ProductSizeBrandColorResponse> getProductColor();

    Page<ProductResponse> getAllProductName(Integer page, Integer size, String name);

    Page<ProductResponse> getPopularProduct(Integer page, Integer size);

}
