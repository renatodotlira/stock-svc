package com.qima.store.controller;

import com.qima.store.dto.CategoryDTO;
import com.qima.store.resource.CategoryResource;
import com.qima.store.service.CategoryService;
import com.qima.store.translate.CategoryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> list() {

        List<CategoryDTO> clientList =
                service
                        .list()
                        .stream()
                        .map(CategoryTranslator::toDto)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(clientList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated CategoryResource categoryResource) {

        return service
                .create(CategoryTranslator.toModel(categoryResource))
                .map(client -> ResponseEntity.ok().build())
                .orElseThrow(RuntimeException::new);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Validated CategoryResource categoryResource) {

        return service
                .update(CategoryTranslator.toEntity(categoryResource, id))
                .map(client -> ResponseEntity.ok().build())
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
