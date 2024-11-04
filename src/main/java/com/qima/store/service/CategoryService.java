package com.qima.store.service;

import com.qima.store.dto.CategoryDTO;
import com.qima.store.repository.CategoryRepository;
import com.qima.store.model.Category;
import com.qima.store.translate.CategoryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Optional<Category> create(Category category) {
        return Optional.of(repository.save(category));
    }

    public Optional<Category> update(Category category) {
        repository.findById(category.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category.not.found"));
        return Optional.of(repository.save(category));
    }

    public CategoryDTO findById(Long id) {
        return repository.findById(id)
                .map(CategoryTranslator::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category.not.found"));
    }

    public void delete(Long id) {
        this.findById(id);
        repository.deleteById(id);
    }

    public List<Category> list() {
        return repository.findAll();
    }

}