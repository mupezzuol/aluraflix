package com.aluraflix.aluraflix.services;

import com.aluraflix.aluraflix.domain.Category;
import com.aluraflix.aluraflix.domain.Video;
import com.aluraflix.aluraflix.exception.ListOfVideoNotFoundException;
import com.aluraflix.aluraflix.exception.VideoAlreadyExistException;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.persistences.VideoRepository;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.filters.VideoFilter;
import com.aluraflix.aluraflix.pojos.forms.VideoForm;
import com.aluraflix.aluraflix.pojos.mappers.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.aluraflix.aluraflix.persistences.specifications.VideoSpecificationBuilder.toSpec;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoMapper videoMapper;
    private final VideoRepository videoRepository;
    private final CategoryService categoryService;

    public Page<VideoDto> getAllVideoDtosPublicAccessFree(final Pageable pageable) {
        var videos = videoRepository.findAllByPublicAccessFreeTrue(pageable);
        if (videos.isEmpty()) {
            throw new ListOfVideoNotFoundException("List of videos public access free is empty.");
        }
        return videos.map(videoMapper::videoToVideoDto);
    }

    public Page<VideoDto> getAllVideoDtos(final VideoFilter videoFilter, final Pageable pageable) throws ListOfVideoNotFoundException {
        var videos = videoRepository.findAll(toSpec(videoFilter), pageable);
        if (videos.isEmpty()) {
            throw new ListOfVideoNotFoundException("List of videos is empty.");
        }
        return videos.map(videoMapper::videoToVideoDto);
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
        Category category = categoryService.getCategory(videoForm.getCategoryId());
        var video = videoMapper.videoFormToVideo(videoForm);
        video.setCategory(category);
        return saveVideo(video);
    }

    @Transactional
    public VideoDto updateVideo(VideoForm videoForm) {
        Video video = getVideo(videoForm.getId());
        Category category = categoryService.getCategory(videoForm.getCategoryId());
        video.setCategory(category);
        video.setTitle(videoForm.getTitle());
        video.setDescription(videoForm.getDescription());
        video.setUrl(videoForm.getUrl());
        video.setPublicAccessFree(videoForm.isPublicAccessFree());
        return saveVideo(video);
    }

    public Video getVideo(final Long id) {
        return videoRepository.findById(id).orElseThrow(
                () -> new VideoNotFoundException("Video with id " + id + " not found."));
    }

    private VideoDto saveVideo(Video video) {
        return videoMapper.videoToVideoDto(videoRepository.save(video));
    }

    private boolean alreadyExistsVideo(String title) {
        return videoRepository.findByTitle(title).isPresent();
    }

}
