package com.qima.store.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private double price;

    private CategoryDTO category;

    private boolean available;

    private boolean active;
}
