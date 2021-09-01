package com.aluraflix.aluraflix.controllers;

import com.aluraflix.aluraflix.pojos.dtos.CategoryDto;
import com.aluraflix.aluraflix.pojos.forms.CategoryForm;
import com.aluraflix.aluraflix.pojos.validations.CategoryFormValidation;
import com.aluraflix.aluraflix.services.CategoryService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Category", description = "Category Service API")
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategoryDtos() {
        return ResponseEntity.ok(categoryService.getAllCategoryDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryDtoById(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(categoryService.getCategoryDtoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") final Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Validated(CategoryFormValidation.CREATE.class)
                                                      @JsonView(CategoryFormValidation.CREATE.class) final CategoryForm categoryForm) {
        return new ResponseEntity<>(categoryService.createCategory(categoryForm), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Validated(CategoryFormValidation.UPDATE.class)
                                                      @JsonView(CategoryFormValidation.UPDATE.class) final CategoryForm categoryForm) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryForm), HttpStatus.OK);
    }

}
