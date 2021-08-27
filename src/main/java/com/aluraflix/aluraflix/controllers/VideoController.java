package com.aluraflix.aluraflix.controllers;

import com.aluraflix.aluraflix.exception.ListOfVideoNotFoundException;
import com.aluraflix.aluraflix.exception.VideoAlreadyExistException;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.form.VideoForm;
import com.aluraflix.aluraflix.services.VideoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Video", description = "Video Service API")
@RequiredArgsConstructor
@RequestMapping("/video")
@RestController
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDto>> getAllVideoDtos() throws ListOfVideoNotFoundException {
        return ResponseEntity.ok(videoService.getAllVideoDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoDtoById(@PathVariable(name = "id") final Long id) throws VideoNotFoundException {
        return ResponseEntity.ok(videoService.getVideoDtoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable(name = "id") final Long id) throws VideoNotFoundException {
        videoService.deleteVideoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody @Valid final VideoForm videoForm) throws VideoAlreadyExistException {
        return new ResponseEntity<>(videoService.createVideo(videoForm), HttpStatus.CREATED);
    }

    // TODO: needs to be added updateVideo
}
