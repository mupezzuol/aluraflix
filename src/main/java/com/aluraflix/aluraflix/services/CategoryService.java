package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.domain.Category;
import com.aluraflix.aluraflix.exception.CategoryNotFoundException;
import com.aluraflix.aluraflix.persistences.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getCategory(final Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id " + id + " not found."));
    }
}
