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
    private String desc;
    private String url;
}
