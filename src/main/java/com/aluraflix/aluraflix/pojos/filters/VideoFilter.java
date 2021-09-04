package com.aluraflix.aluraflix.pojos.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoFilter {

    private String title;
    private String description;
    private boolean publicAccessFree;
    private String categoryTitle;

    public static VideoFilter createFilter(final String title, final String description, final boolean publicAccessFree, final String categoryTitle) {
        return VideoFilter.builder()
                .title(title)
                .description(description)
                .publicAccessFree(publicAccessFree)
                .categoryTitle(categoryTitle)
                .build();
    }
}
