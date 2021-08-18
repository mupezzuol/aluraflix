package com.aluraflix.aluraflix.pojos.mappers;

import com.aluraflix.aluraflix.entities.Video;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface VideoMapper {
    VideoDto videoToVideoDto(Video video);
    List<VideoDto> videosToVideoDtos(List<Video> video);
    Video videoDtoToVideo(VideoDto videoDto);
}
