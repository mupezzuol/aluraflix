package com.aluraflix.aluraflix.controllers;

import com.aluraflix.aluraflix.exception.ListOfVideoNotFoundException;
import com.aluraflix.aluraflix.exception.VideoAlreadyExistException;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.filters.VideoFilter;
import com.aluraflix.aluraflix.pojos.validations.VideoFormValidation;
import com.aluraflix.aluraflix.pojos.forms.VideoForm;
import com.aluraflix.aluraflix.services.VideoService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Video", description = "Video Service API")
@RequiredArgsConstructor
@RequestMapping("/video")
@RestController
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/free")
    public ResponseEntity<Page<VideoDto>> getAllVideoDtosPublicAccessFree(@PageableDefault(size = 5, sort = "title", direction = Sort.Direction.ASC) final Pageable pageable) throws ListOfVideoNotFoundException {
        return ResponseEntity.ok(videoService.getAllVideoDtosPublicAccessFree(pageable));
    }

    @GetMapping
    public ResponseEntity<Page<VideoDto>> getAllVideoDtos(@RequestParam(value = "title", required = false) final String title,
                                                          @RequestParam(value = "description", required = false) final String description,
                                                          @RequestParam(value = "publicAccessFree", required = false) final boolean publicAccessFree,
                                                          @RequestParam(value = "categoryTitle", required = false) final String categoryTitle,
                                                          @PageableDefault(size = 5, sort = "title", direction = Sort.Direction.ASC) final Pageable pageable) throws ListOfVideoNotFoundException {
        var videoFilter = VideoFilter.createFilter(title, description, publicAccessFree, categoryTitle);
        return ResponseEntity.ok(videoService.getAllVideoDtos(videoFilter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoDtoById(@PathVariable(name = "id") final Long id) throws VideoNotFoundException {
        return ResponseEntity.ok(videoService.getVideoDtoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable(name = "id") final Long id) throws VideoNotFoundException {
        videoService.deleteVideoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Validated(VideoFormValidation.CREATE.class)
                                                @JsonView(VideoFormValidation.CREATE.class) final VideoForm videoForm) throws VideoAlreadyExistException {
        return new ResponseEntity<>(videoService.createVideo(videoForm), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<VideoDto> updateVideo(@RequestBody @Validated(VideoFormValidation.UPDATE.class)
                                                @JsonView(VideoFormValidation.UPDATE.class) final VideoForm videoForm) {
        return new ResponseEntity<>(videoService.updateVideo(videoForm), HttpStatus.OK);
    }

}
