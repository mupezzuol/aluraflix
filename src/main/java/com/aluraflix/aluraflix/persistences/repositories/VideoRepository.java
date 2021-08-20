package com.aluraflix.aluraflix.persistences.repositories;

import com.aluraflix.aluraflix.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
