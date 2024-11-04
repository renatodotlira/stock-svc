package com.qima.store.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String categoryParh;
    private boolean available;
    private boolean active;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
