package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.domain.Category;
import com.aluraflix.aluraflix.exception.*;
import com.aluraflix.aluraflix.persistences.CategoryRepository;
import com.aluraflix.aluraflix.pojos.dtos.CategoryDto;
import com.aluraflix.aluraflix.pojos.forms.CategoryForm;
import com.aluraflix.aluraflix.pojos.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public Page<CategoryDto> getAllCategoryDtos(final Pageable pageable) {
        var categories = categoryRepository.findAll(pageable);
        if (categories.isEmpty()) {
            throw new ListOfCategoryNotFoundException("List of categories is empty.");
        }
        return categories.map(categoryMapper::categoryToCategoryDto);
    }

    public CategoryDto getCategoryDtoById(Long id) {
        return categoryMapper.categoryToCategoryDto(getCategory(id));
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        Category category = getCategory(id);
        if (!category.getVideos().isEmpty()) {
            throw new CategoryContainVideosException("It is not allowed to delete Category with id " + id +
                    " there are videos linked, please unlink videos before deleting.");
        }
        categoryRepository.delete(category);
    }

    @Transactional
    public CategoryDto createCategory(CategoryForm categoryForm) {
        if (alreadyExistsCategory(categoryForm.getTitle())) {
            throw new CategoryAlreadyExistException("Category with Title '" + categoryForm.getTitle() + "' already exists.");
        }
        var category = categoryMapper.categoryFormToCategory(categoryForm);
        return saveCategory(category);
    }

    @Transactional
    public CategoryDto updateCategory(CategoryForm categoryForm) {
        if (alreadyExistsCategory(categoryForm.getTitle())) {
            throw new CategoryAlreadyExistException("Category with Title '" + categoryForm.getTitle() + "' already exists.");
        }
        var category = getCategory(categoryForm.getId());
        category.setTitle(categoryForm.getTitle());
        category.setColour(categoryForm.getColour());
        return saveCategory(category);
    }

    private CategoryDto saveCategory(Category category) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    public Category getCategory(final Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id " + id + " not found."));
    }

    private boolean alreadyExistsCategory(String title) {
        return categoryRepository.findByTitle(title).isPresent();
    }
}
