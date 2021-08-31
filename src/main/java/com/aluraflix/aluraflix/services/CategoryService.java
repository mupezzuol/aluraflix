package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.domain.Category;
import com.aluraflix.aluraflix.exception.CategoryContainVideosException;
import com.aluraflix.aluraflix.exception.CategoryNotFoundException;
import com.aluraflix.aluraflix.exception.ListOfCategoryNotFoundException;
import com.aluraflix.aluraflix.persistences.CategoryRepository;
import com.aluraflix.aluraflix.pojos.dtos.CategoryDto;
import com.aluraflix.aluraflix.pojos.forms.CategoryForm;
import com.aluraflix.aluraflix.pojos.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategoryDtos() {
        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ListOfCategoryNotFoundException("List of categories is empty.");
        }
        return categoryMapper.categoriesToCategoryDtos(categories);
    }

    public CategoryDto getCategoryDtoById(Long id) {
        return categoryMapper.categoryToCategoryDto(getCategory(id));
    }

    @Transactional
    public void deleteCategoryById(Long id) {
        Category category = getCategory(id);
        if (!category.getVideos().isEmpty())
        {
            throw new CategoryContainVideosException("It is not allowed to delete Category with id " + id +
                    " there are videos linked, please unlink videos before deleting.");
        }
        categoryRepository.delete(category);
    }

    @Transactional
    public CategoryDto createCategory(CategoryForm categoryForm) {
        return null;
    }

    @Transactional
    public CategoryDto updateCategory(CategoryForm categoryForm) {
        return null;
    }

    public Category getCategory(final Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id " + id + " not found."));
    }
}
