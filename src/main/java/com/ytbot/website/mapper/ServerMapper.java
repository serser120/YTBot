package com.ytbot.website.mapper;

import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.ServerVideoHistoryDTO;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
import com.ytbot.website.repository.UserRepository;
import com.ytbot.website.repository.VideoRepository;
import com.ytbot.website.service.ServerVideoHistoryService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ServerMapper extends GenericMapper<Server, ServerDTO> {
    private final ServerVideoHistoryRepository serverVideoHistoryRepository;
    private final ServerVideoHistoryService serverVideoHistoryService;




    protected ServerMapper(ModelMapper modelMapper,
                           ServerVideoHistoryRepository serverVideoHistoryRepository,
                           ServerVideoHistoryService serverVideoHistoryService) {
        super(modelMapper, Server.class, ServerDTO.class);
        this.serverVideoHistoryRepository = serverVideoHistoryRepository;
        this.serverVideoHistoryService=serverVideoHistoryService;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Server.class, ServerDTO.class)
                .addMappings(m -> m.skip(ServerDTO::setServerVideoHistories)).setPostConverter(toDTOConverter())
                .addMappings(m -> m.skip(ServerDTO::setServerVideoHistoryDTOS)).setPostConverter(toDTOConverter());
        modelMapper.createTypeMap(ServerDTO.class, Server.class)
                .addMappings(m -> m.skip(Server::setServerVideoHistories)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Server::setUsers)).setPostConverter(toEntityConverter());
    }


    @Override
    public void mapSpecificFields(ServerDTO source, Server destination) {
        if (!Objects.isNull(source.getServerVideoHistories()) && source.getServerVideoHistories().size() > 0) {
            destination.setServerVideoHistories(new HashSet<>(serverVideoHistoryRepository.findAllById(source.getServerVideoHistories())));
        } else {
            destination.setServerVideoHistories(Collections.emptySet());
        }
    }

    @Override
    public void mapSpecificFields(Server source, ServerDTO destination) {
        destination.setServerVideoHistories(getVideosIds(source));
        destination.setServerVideoHistories(source.getServerVideoHistories().stream().map(GenericModel::getId)
                .collect(Collectors.toSet()));
        Set<ServerVideoHistoryDTO> serverVideoHistoryDTOS = new HashSet<>();
        source.getServerVideoHistories().forEach(model -> {
            serverVideoHistoryDTOS.add(serverVideoHistoryService.getOne(model.getId()));
        });
        destination.setServerVideoHistoryDTOS(serverVideoHistoryDTOS);
    }

    private Set<Long> getVideosIds(Server server) {
        return Objects.isNull(server) || Objects.isNull(server.getServerVideoHistories())
                ? null
                : server.getServerVideoHistories().stream().map(GenericModel::getId)
                .collect(Collectors.toSet());
    }

    private Set<Long> getUsersIds(Server server) {
        return Objects.isNull(server) || Objects.isNull(server.getUsers())
                ? null
                : server.getUsers().stream().map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
