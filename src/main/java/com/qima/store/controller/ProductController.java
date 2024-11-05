package com.qima.store.controller;

import com.qima.store.dto.ProductDTO;
import com.qima.store.resource.ProductResource;
import com.qima.store.service.ProductService;
import com.qima.store.translate.ProductTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));
        Page<ProductDTO> productList = service.list(name, category, pageable);
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated ProductResource productResource) {
        return service
                .create(ProductTranslator.toModel(productResource))
                .map(product -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated ProductResource productResource) {
        return service
                .update(ProductTranslator.toEntity(productResource, id))
                .map(product -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(service
                .findById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

}
