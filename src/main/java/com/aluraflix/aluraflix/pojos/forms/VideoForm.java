package com.aluraflix.aluraflix.pojos.forms;

import com.aluraflix.aluraflix.pojos.validations.VideoFormValidation;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoForm {

    @JsonView(VideoFormValidation.UPDATE.class)
    @NotNull(message = "id cannot be null", groups = VideoFormValidation.UPDATE.class)
    private Long id;

    @JsonView({VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @NotBlank(message = "title cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String title;

    @JsonView({VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @NotBlank(message = "description cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @Size(min = 5, message = "size must be greater than 5 characters", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String description;

    @JsonView({VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @NotBlank(message = "url cannot be null or empty", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @Size(min = 5, message = "size must be greater than 5 characters", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private String url;

    @JsonView({VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    @NotNull(message = "id cannot be null", groups = {VideoFormValidation.CREATE.class, VideoFormValidation.UPDATE.class})
    private Long categoryId;
}
