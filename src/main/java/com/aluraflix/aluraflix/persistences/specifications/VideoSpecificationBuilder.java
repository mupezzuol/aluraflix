package com.aluraflix.aluraflix.persistences.specifications;

import com.aluraflix.aluraflix.domain.Category_;
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

        if (videoFilter.getDescription() != null) {
            specification = byDescription(videoFilter.getDescription()).and(specification);
        }

        if (videoFilter.getCategoryTitle() != null) {
            specification = byCategoryTitle(videoFilter.getCategoryTitle()).and(specification);
        }

        specification = byPublicAccessFree(videoFilter.isPublicAccessFree()).and(specification);

        return specification;
    }

    private static Specification<Video> byTitle(final String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Video_.TITLE)),
                "%" + title.toLowerCase() + "%");
    }

    private static Specification<Video> byDescription(final String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Video_.DESCRIPTION)),
                "%" + description.toLowerCase() + "%");
    }

    private static Specification<Video> byCategoryTitle(final String categoryTitle) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.lower(
                        root.get(Video_.CATEGORY).get(Category_.TITLE)),
                categoryTitle.toLowerCase());
    }

    private static Specification<Video> byPublicAccessFree(final boolean publicAccessFree) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Video_.PUBLIC_ACCESS_FREE), publicAccessFree);
    }

}
