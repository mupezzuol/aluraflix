package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.persistences.repositories.VideoRepository;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public List<VideoDto> findAllVideos() {
        List<VideoDto> videoDtos = videoMapper.videosToVideoDtos(videoRepository.findAll());
        return videoDtos;
    }
}
