package com.ytbot.website.service;


import com.ytbot.VideoTestData;
import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.mapper.VideoMapper;
import com.ytbot.website.model.Video;
import com.ytbot.website.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class VideoServiceTest extends GenericTest<Video, VideoDTO> {

    private final VideoRepository videoRepository = Mockito.mock(VideoRepository.class);
    private final VideoMapper videoMapper = Mockito.mock(VideoMapper.class);

    public VideoServiceTest(){
        super();
        service = new VideoService(videoRepository, videoMapper);
    }

    @Test
    @Order(1)
    @Override
    protected void getAll() {
        Mockito.when(videoRepository.findAll()).thenReturn(VideoTestData.VIDEO_LIST);
        Mockito.when(videoMapper.toDTOs(VideoTestData.VIDEO_LIST)).thenReturn(VideoTestData.VIDEO_DTO_LIST);
        List<VideoDTO> videoDTOS = service.listAll();
        assertEquals(VideoTestData.VIDEO_LIST.size(), videoDTOS.size());
    }

    @Test
    @Order(2)
    @Override
    protected void getOne() {
        Mockito.when(videoRepository.findById(1L)).thenReturn(Optional.of(VideoTestData.video1));
        Mockito.when(videoMapper.toDTO(VideoTestData.video1)).thenReturn(VideoTestData.videoDTO1);
        VideoDTO videoDTO = service.getOne(1L);
        log.info("Testing getOne(): " + videoDTO);
        assertEquals(VideoTestData.videoDTO1, videoDTO);
    }

    @Test
    @Order(3)
    @Override
    protected void create() {
        Mockito.when(videoMapper.toEntity(VideoTestData.videoDTO1)).thenReturn(VideoTestData.video1);
        Mockito.when(videoMapper.toDTO(VideoTestData.video1)).thenReturn(VideoTestData.videoDTO1);
        Mockito.when(videoRepository.save(VideoTestData.video1)).thenReturn(VideoTestData.video1);
        VideoDTO videoDTO = service.create(VideoTestData.videoDTO1);
        log.info("Testing create(): " + videoDTO);
        assertEquals(VideoTestData.videoDTO1, videoDTO);
    }

    @Test
    @Order(4)
    @Override
    protected void update() {
        Mockito.when(videoMapper.toEntity(VideoTestData.videoDTO1)).thenReturn(VideoTestData.video1);
        Mockito.when(videoMapper.toDTO(VideoTestData.video1)).thenReturn(VideoTestData.videoDTO1);
        Mockito.when(videoRepository.save(VideoTestData.video1)).thenReturn(VideoTestData.video1);
        VideoDTO videoDTO = service.update(VideoTestData.videoDTO1);
        log.info("Testing update(): " + videoDTO);
        assertEquals(VideoTestData.videoDTO1, videoDTO);
    }

    @Test
    @Order(5)
    protected void findVideos() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "numberOfPlays"));
        Mockito.when(videoRepository.searchVideos("title1", "url1", pageRequest))
                .thenReturn(new PageImpl<>(VideoTestData.VIDEO_LIST));
        Mockito.when(videoMapper.toDTOs(VideoTestData.VIDEO_LIST)).thenReturn(VideoTestData.VIDEO_DTO_LIST);
        Page<VideoDTO> videoDTOS = ((VideoService)service).findVideos(VideoTestData.videoSearchDTO, pageRequest);
        log.info("Testing findVideos(): " + videoDTOS);
        assertEquals(VideoTestData.VIDEO_DTO_LIST, videoDTOS.getContent());
    }
}
