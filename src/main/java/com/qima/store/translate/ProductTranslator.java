package com.qima.store.translate;

import com.qima.store.dto.ProductDTO;
import com.qima.store.model.Product;
import com.qima.store.resource.ProductResource;

import jakarta.validation.constraints.NotNull;

public class ProductTranslator {

    public static Product toModel(@NotNull final ProductResource from) {

        return Product.builder()
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .category(from.getCategory() != null ? CategoryTranslator.toModel(from.getCategory()) : null)
                .available(from.isAvailable())
                .build();
    }

    public static Product toModel(@NotNull final ProductDTO from) {

        return Product.builder()
                .id(from.getId())
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .category(from.getCategory() != null ? CategoryTranslator.toModel(from.getCategory()) : null)
                .available(from.isAvailable())
                .active(from.isActive())
                .build();
    }

    public static ProductDTO toDto(@NotNull final Product from){

        return ProductDTO.builder()
                .id(from.getId())
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .category(CategoryTranslator.toDto(from.getCategory()))
                .available(from.isAvailable())
                .active(from.isActive())
                .build();
    }

    public static @NotNull Product toEntity(ProductResource from, Long id) {
        return Product.builder()
                .id(id)
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .category(CategoryTranslator.toModel(from.getCategory()))
                .available(from.isAvailable())
                .build();

    }
}
