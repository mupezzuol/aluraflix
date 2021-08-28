package com.aluraflix.aluraflix.pojos.mappers;

import com.aluraflix.aluraflix.domain.Video;
import com.aluraflix.aluraflix.pojos.dtos.VideoDto;
import com.aluraflix.aluraflix.pojos.forms.VideoForm;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoDto videoToVideoDto(Video video);

    List<VideoDto> videosToVideoDtos(List<Video> video);

    /**
     * Transforms POJO into Entity, but fields that have relationships will be set to null.
     *
     * @param videoForm object used for create. (cannot be used for update/entity)
     * @return Video entity.
     */
    @InheritInverseConfiguration
    Video videoFormToVideo(VideoForm videoForm);
}
