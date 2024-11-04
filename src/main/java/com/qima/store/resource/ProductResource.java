package com.qima.store.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qima.store.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResource {

    @JsonProperty(value="name", required = true)
    @NotNull(message="product.name.required")
    private String name;

    @JsonProperty(value="description", required = true)
    @NotNull(message="product.description.required")
    private String description;

    @JsonProperty(value="price", required = true)
    @NotNull(message="product.price.required")
    private Double price;

    @JsonProperty(value="category", required = true)
    private CategoryDTO category;

    private boolean available;

}
