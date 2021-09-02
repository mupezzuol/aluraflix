package com.aluraflix.aluraflix.persistences;

import com.aluraflix.aluraflix.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, JpaSpecificationExecutor<Video> {

    Optional<Video> findByTitle(String title);

    List<Video> findAllByPublicAccessFreeTrue();

    Page<Video> findAllByVideoFilter(Specification<Video> specification, Pageable pageable);
}
