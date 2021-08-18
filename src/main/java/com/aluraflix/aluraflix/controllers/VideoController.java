package com.aluraflix.aluraflix.controllers;

import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.services.VideoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Video", description = "Video Service API")
@RequiredArgsConstructor
@RequestMapping("/video")
@RestController
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<List<VideoDto>> getVideos() {
        var videos = videoService.findAllVideos();
        return ResponseEntity.ok(videos);
    }
}
