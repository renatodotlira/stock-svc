package com.qima.store.service;

import com.qima.store.dto.ProductDTO;
import com.qima.store.model.Product;
import com.qima.store.repository.ProductRepository;
import com.qima.store.translate.ProductTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    public Optional<Product> create(@NotNull Product product) {
        categoryService.findById(product.getCategory().getId());
        return Optional.of(repository.save(product));
    }

    public Optional<Product> update(@NotNull Product product) {
        repository.findById(product.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product.not.found"));
        return Optional.of(repository.save(product));
    }

    public ProductDTO findById(@NotNull Long id) {
        return repository.findById(id)
                .map(ProductTranslator::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product.not.found"));
    }

    public void delete(@NotNull Long id) {
        ProductDTO productDTO = this.findById(id);
        productDTO.setActive(false);
        repository.save(ProductTranslator.toModel(productDTO));
    }

    public Page<ProductDTO> list(String name, String category, Pageable pageable) {
        Page<Product> products;
        if (name != null && category != null) {
            products = repository.findByNameContainingAndCategoryNameContainingAndActiveTrue(name, category, pageable);
        } else if (name != null) {
            products = repository.findByNameContainingAndActiveTrue(name, pageable);
        } else if (category != null) {
            products = repository.findByCategoryNameContainingAndActiveTrue(category, pageable);
        } else {
            products = repository.findByActiveTrue(pageable);
        }
        return products.map(ProductTranslator::toDto);
    }

}