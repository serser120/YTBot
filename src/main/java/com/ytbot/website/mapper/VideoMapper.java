package com.ytbot.website.mapper;

import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.model.Video;
import com.ytbot.website.repository.ServerRepository;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VideoMapper extends GenericMapper<Video, VideoDTO> {

    protected VideoMapper(ModelMapper modelMapper,
                          ServerVideoHistoryRepository serverVideoHistoryRepository){
        super(modelMapper, Video.class, VideoDTO.class);
    }

    @Override
    public void mapSpecificFields(VideoDTO source, Video destination) {
//        System.out.println("mapSpecificFields(VideoDTO source, Video destination)"+ source.getYouTubeIdentifier().substring(29));
//        destination.setYouTubeIdentifier(source.getYouTubeIdentifier().substring(29));
//        if (!Objects.isNull(source.getServerVideoHistories()) && source.getServerVideoHistories().size() > 0) {
//            destination.setServerVideoHistories(new HashSet<>(serverVideoHistoryRepository.findAllById(source.getServerVideoHistories())));
//        } else {
//            destination.setServerVideoHistories(Collections.emptySet());
//        }
    }

    @Override
    public void mapSpecificFields(Video source, VideoDTO destination) {
//        System.out.println("mapSpecificFields(Video source, VideoDTO destination)"+ "https://www.youtube.com/embed/" + source.getYouTubeIdentifier());
//        destination.setYouTubeIdentifier("https://www.youtube.com/embed/" + source.getYouTubeIdentifier());
//        destination.setServerVideoHistories(getIds(source));
    }


    private Set<Long> getIds(Video video) {
        return Objects.isNull(video) || Objects.isNull(video.getServerVideoHistories())
                ? null
                : video.getServerVideoHistories().stream().map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}


