package com.aluraflix.aluraflix.pojos.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoForm {

    @NotBlank(message = "title cannot be null or empty")
    private String title;

    @NotBlank(message = "description cannot be null or empty")
    @Size(min = 5, message = "size must be greater than 5 characters")
    private String description;

    @NotBlank(message = "url cannot be null or empty")
    @Size(min = 5, message = "size must be greater than 5 characters")
    private String url;
}
