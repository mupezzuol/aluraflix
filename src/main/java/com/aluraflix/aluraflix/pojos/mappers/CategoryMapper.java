package com.aluraflix.aluraflix.pojos.mappers;

import com.aluraflix.aluraflix.domain.Category;
import com.aluraflix.aluraflix.pojos.dtos.CategoryDto;
import com.aluraflix.aluraflix.pojos.forms.CategoryForm;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoriesToCategoryDtos(List<Category> category);

    /**
     * Transforms POJO into Entity, but fields that have relationships will be set to null.
     *
     * @param categoryForm object used for create. (cannot be used for update/entity)
     * @return Category entity.
     */
    @InheritInverseConfiguration
    Category categoryFormToCategory(CategoryForm categoryForm);
}
