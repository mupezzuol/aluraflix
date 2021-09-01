package com.aluraflix.aluraflix.pojos.forms;

import com.aluraflix.aluraflix.pojos.validations.CategoryFormValidation;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {

    @JsonView(CategoryFormValidation.UPDATE.class)
    @NotNull(message = "id cannot be null", groups = CategoryFormValidation.UPDATE.class)
    private Long id;

    @JsonView({CategoryFormValidation.CREATE.class, CategoryFormValidation.UPDATE.class})
    @NotBlank(message = "title cannot be null or empty", groups = {CategoryFormValidation.CREATE.class, CategoryFormValidation.UPDATE.class})
    private String title;

    @JsonView({CategoryFormValidation.CREATE.class, CategoryFormValidation.UPDATE.class})
    @NotBlank(message = "colour cannot be null or empty", groups = {CategoryFormValidation.CREATE.class, CategoryFormValidation.UPDATE.class})
    @Size(min = 7, max = 7, message = "size must be 7 characters", groups = {CategoryFormValidation.CREATE.class, CategoryFormValidation.UPDATE.class})
    private String colour;
}
