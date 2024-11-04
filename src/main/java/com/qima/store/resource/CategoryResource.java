package com.qima.store.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResource {

    @JsonProperty(value="name", required = true)
    @NotNull(message="category.mane.required")
    private String name;

    private CategoryResource category;

}
