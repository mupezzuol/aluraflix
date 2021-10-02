package com.aluraflix.aluraflix.pojos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable  {
    private Long id;
    private String title;
    private String colour;
}
