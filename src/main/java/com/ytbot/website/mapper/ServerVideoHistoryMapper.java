package com.ytbot.website.mapper;

import com.ytbot.website.dto.ServerVideoHistoryDTO;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.repository.ServerRepository;
import com.ytbot.website.repository.VideoRepository;
import com.ytbot.website.service.ServerService;
import com.ytbot.website.service.VideoService;
import com.ytbot.website.utils.DateFormatterUtils;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.Set;

@Component
public class ServerVideoHistoryMapper extends GenericMapper<ServerVideoHistory, ServerVideoHistoryDTO> {

    private final ServerRepository serverRepository;
    private final VideoRepository videoRepository;
    private final VideoService videoService;


    protected ServerVideoHistoryMapper(ModelMapper mapper,
                                       ServerRepository serverRepository,
                                       VideoRepository videoRepository,
                                       VideoService videoService) {
        super(mapper, ServerVideoHistory.class, ServerVideoHistoryDTO.class);
        this.serverRepository = serverRepository;
        this.videoRepository = videoRepository;
        this.videoService = videoService;
    }

    @PostConstruct
    public void setupMapper() {
        super.modelMapper.createTypeMap(ServerVideoHistory.class, ServerVideoHistoryDTO.class)
                .addMappings(m -> m.skip(ServerVideoHistoryDTO::setServerId)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(ServerVideoHistoryDTO::setVideoId)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(ServerVideoHistoryDTO::setVideoDTO)).setPostConverter(toDTOConverter());

        super.modelMapper.createTypeMap(ServerVideoHistoryDTO.class, ServerVideoHistory.class)
                .addMappings(m -> m.skip(ServerVideoHistory::setVideo)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ServerVideoHistory::setServer)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ServerVideoHistoryDTO source, ServerVideoHistory destination) {
        destination.setVideo(videoRepository.findById(source.getVideoId()).orElseThrow(() -> new NotFoundException("Видео не найдено")));
        destination.setServer(serverRepository.findById(source.getServerId()).orElseThrow(() -> new NotFoundException("Сервер не найден")));
        destination.setPlaybackDate(DateFormatterUtils.formatDate(source.getPlaybackDate()));
    }

    @Override
    public void mapSpecificFields(ServerVideoHistory source, ServerVideoHistoryDTO destination) {
        destination.setServerId(source.getServer().getId());
        destination.setVideoId(source.getVideo().getId());
        destination.setVideoDTO(videoService.getOne(source.getVideo().getId()));
        destination.setPlaybackDate(DateFormatterUtils.formatDate(source.getPlaybackDate()));
    }

    protected Set<Long> getIds(ServerVideoHistory entity) {
        throw new UnsupportedOperationException("Метод недоступен");
    }

}
