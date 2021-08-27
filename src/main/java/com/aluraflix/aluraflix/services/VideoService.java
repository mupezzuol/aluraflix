package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.domain.Video;
import com.aluraflix.aluraflix.exception.ListOfVideoNotFoundException;
import com.aluraflix.aluraflix.exception.VideoAlreadyExistException;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.persistences.repositories.VideoRepository;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.form.VideoForm;
import com.aluraflix.aluraflix.pojos.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoMapper videoMapper;
    private final VideoRepository videoRepository;

    public Video getVideo(final Long id) {
        return videoRepository.findById(id).orElseThrow(
                () -> new VideoNotFoundException("Video with id " + id + " not found."));
    }

    public List<VideoDto> getAllVideoDtos() throws ListOfVideoNotFoundException {
        var videos = videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new ListOfVideoNotFoundException("List of videos is empty.");
        }
        return videoMapper.videosToVideoDtos(videos);
    }

    public VideoDto getVideoDtoById(Long id) {
        return videoMapper.videoToVideoDto(getVideo(id));
    }

    @Transactional
    public void deleteVideoById(Long id) {
        videoRepository.delete(getVideo(id));
    }

    @Transactional
    public VideoDto createVideo(VideoForm videoForm) throws VideoAlreadyExistException {
        if (alreadyExistsVideo(videoForm.getTitle())) {
            throw new VideoAlreadyExistException("Video with Title '" + videoForm.getTitle() + "' already exists.");
        }
        var video = Video.builder()
                .title(videoForm.getTitle())
                .description(videoForm.getDescription())
                .url(videoForm.getUrl())
                .build();
        return videoMapper.videoToVideoDto(videoRepository.save(video));
    }

    private boolean alreadyExistsVideo(String title) {
        return videoRepository.findByTitle(title).isPresent();
    }
}
