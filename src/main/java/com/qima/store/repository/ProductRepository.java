package com.qima.store.repository;

import com.qima.store.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContainingAndActiveTrue(String name, Pageable pageable);
    Page<Product> findByCategoryNameContainingAndActiveTrue(String category, Pageable pageable);
    Page<Product> findByNameContainingAndCategoryNameContainingAndActiveTrue(String name, String category, Pageable pageable);
    Page<Product> findByActiveTrue(Pageable pageable);

}