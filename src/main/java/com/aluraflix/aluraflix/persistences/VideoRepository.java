package com.aluraflix.aluraflix.persistences;

import com.aluraflix.aluraflix.domain.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, JpaSpecificationExecutor<Video> {

    Optional<Video> findByTitle(String title);

    Page<Video> findAllByPublicAccessFreeTrue(Pageable pageable);
}
