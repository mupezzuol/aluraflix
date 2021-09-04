package com.aluraflix.aluraflix.pojos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private boolean publicAccessFree;
    private CategoryDto category;
}
