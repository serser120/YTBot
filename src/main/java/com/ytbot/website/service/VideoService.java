package com.ytbot.website.service;

import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.dto.VideoSearchDTO;
import com.ytbot.website.mapper.VideoMapper;
import com.ytbot.website.model.Video;
import com.ytbot.website.repository.VideoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService extends GenericService<Video, VideoDTO> {
    protected final VideoRepository repository;
    protected final VideoMapper mapper;

    public VideoService(VideoRepository repository, VideoMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<VideoDTO> getAllVideos(Pageable pageable) {
        Page<Video> videoPage = repository.findAll(pageable);
        List<VideoDTO> videoDTOList = mapper.toDTOs(videoPage.getContent());
        return new PageImpl<>(videoDTOList, pageable, videoPage.getTotalElements());
    }
    public VideoDTO getVideo(Long id) {
       return mapper.toDTO(mapper.toEntity(super.getOne(id)));
    }

    public Page<VideoDTO> findVideos(VideoSearchDTO videoSearchDTO, Pageable pageable) {
        Page<Video> videoPage = repository.searchVideos(videoSearchDTO.getTitle(),
                videoSearchDTO.getUrl(),
                pageable);
        List<VideoDTO> result = mapper.toDTOs(videoPage.getContent());
        return new PageImpl<>(result, pageable, videoPage.getTotalElements());
    }
}
