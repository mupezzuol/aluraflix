package com.aluraflix.aluraflix.pojos.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoForm {

    @NotNull(message = "id cannot be null", groups = VideoFormValidation.UPDATE.class)
    private Long id;

    @NotBlank(message = "title cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String title;

    @NotBlank(message = "description cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @Size(min = 5, message = "size must be greater than 5 characters", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String description;

    @NotBlank(message = "url cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @Size(min = 5, message = "size must be greater than 5 characters", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String url;
}
