package com.aluraflix.aluraflix.services;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.aluraflix.aluraflix.domain.Video;
import com.aluraflix.aluraflix.exception.VideoNotFoundException;
import com.aluraflix.aluraflix.persistences.VideoRepository;
import com.aluraflix.aluraflix.pojos.mappers.VideoMapper;

@ExtendWith( MockitoExtension.class )
class VideoServiceTest
{
    @Mock
    private VideoMapper videoMapper;
    @Mock
    private VideoRepository videoRepository;
    @Mock
    private CategoryService categoryService;

    private VideoService videoService;

    @BeforeEach
    void setup()
    {
        videoService = new VideoService( videoMapper, videoRepository, categoryService );
    }

    @Test
    void testGetVideo()
    {
        Video video = getMockVideo();
        when( videoRepository.findById( any() ) ).thenReturn( Optional.ofNullable( video ) );
        Video videoFound = videoService.getVideo( 1L );
        assertEquals( video.getDescription(), videoFound.getDescription() );
    }

    @Test
    void testGetVideoNotFoundException()
    {
        Video video = getMockVideo();
        when( videoRepository.findById( any() ) ).thenReturn( Optional.empty() );
        assertThatThrownBy( () -> videoService.getVideo( video.getId() ) )
            .isInstanceOf( VideoNotFoundException.class )
            .hasMessageContaining( String.format( "Video with id %s not found.", video.getId() ) );
    }

    private Video getMockVideo()
    {
        return Video.builder()
            .id( 1L )
            .description( "Description Test" )
            .build();

    }

}
