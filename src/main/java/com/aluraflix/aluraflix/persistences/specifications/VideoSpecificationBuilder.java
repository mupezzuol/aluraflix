package com.aluraflix.aluraflix.persistences.specifications;

import com.aluraflix.aluraflix.domain.Video;
import com.aluraflix.aluraflix.domain.Video_;
import com.aluraflix.aluraflix.pojos.filters.VideoFilter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VideoSpecificationBuilder {

    public static Specification<Video> toSpec(final VideoFilter videoFilter) {
        Specification<Video> specification = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (videoFilter.getTitle() != null) {
            specification = byTitle(videoFilter.getTitle()).and(specification);
        }

        return specification;
    }

    private static Specification<Video> byTitle(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Video_.TITLE), title);
    }


}
