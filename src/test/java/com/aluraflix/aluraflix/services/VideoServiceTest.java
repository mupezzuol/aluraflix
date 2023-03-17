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
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.mappers.VideoMapper;

@ExtendWith( MockitoExtension.class )
class VideoServiceTest
{
    public static final long ID = 1L;
    public static final String DESCRIPTION = "Description Test";
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
        Video videoFound = videoService.getVideo( ID );
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

    @Test
    void testSaveVideo()
    {
        Video video = getMockVideo();
        VideoDto videoDto = getMockVideoDto();

        when( videoMapper.videoToVideoDto( any() ) ).thenReturn( videoDto );
        when( videoRepository.save( any() ) ).thenReturn( video );

        VideoDto videoDtoFound = videoService.saveVideo( video );
        assertEquals( video.getDescription(), videoDto.getDescription() );
    }

    private VideoDto getMockVideoDto()
    {
        return VideoDto.builder()
            .id( ID )
            .description( DESCRIPTION )
            .build();
    }

    private Video getMockVideo()
    {
        return Video.builder()
            .id( ID )
            .description( DESCRIPTION )
            .build();

    }

}
