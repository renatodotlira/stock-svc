package com.qima.store.translate;

import com.qima.store.dto.CategoryDTO;
import com.qima.store.model.Category;
import com.qima.store.resource.CategoryResource;

import jakarta.validation.constraints.NotNull;

public class CategoryTranslator {

    public static Category toModel(final CategoryResource from){
        if (from == null) return null;
        return Category.builder()
                .name(from.getName())
                .category(CategoryTranslator.toModel(from.getCategory()))
                .build();
    }

    public static Category toModel(final CategoryDTO from){
        if (from == null) return null;
        return Category.builder()
                .id(from.getId())
                .name(from.getName())
                .category(CategoryTranslator.toModel(from.getCategory()))
                .build();
    }

    public static Category toEntity(@NotNull final CategoryResource from, @NotNull Long id){

        return Category.builder()
                .id(id)
                .name(from.getName())
                .build();
    }

    public static CategoryDTO toDto(final Category from){
        if (from == null) return null;
        return CategoryDTO.builder()
                .id(from.getId())
                .name(from.getName())
                .category(CategoryTranslator.toDto(from.getCategory()))
                .build();
    }

}
