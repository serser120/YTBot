package com.ytbot.website.service;

import com.ytbot.website.dto.*;
import com.ytbot.website.mapper.ServerMapper;
import com.ytbot.website.mapper.ServerVideoHistoryMapper;
import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.model.User;
import com.ytbot.website.repository.ServerRepository;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ServerService extends GenericService<Server, ServerDTO>{
    protected final ServerRepository repository;
    protected final ServerMapper serverMapper;
    public final ServerVideoHistoryMapper serverVideoHistoryMapper;
    public final ServerVideoHistoryRepository serverVideoHistoryRepository;

    protected ServerService(ServerRepository repository,
                            ServerMapper serverMapper,
                            ServerVideoHistoryMapper serverVideoHistoryMapper,
                            ServerVideoHistoryRepository serverVideoHistoryRepository) {
        super(repository, serverMapper);
        this.repository = repository;
        this.serverMapper = serverMapper;
        this.serverVideoHistoryMapper = serverVideoHistoryMapper;
        this.serverVideoHistoryRepository = serverVideoHistoryRepository;
    }

    public List<ServerDTO> getAllServers(){
        return serverMapper.toDTOs(repository.findAll());
    }

    public ServerDTO getServer(Long id) {
        return serverMapper.toDTO(serverMapper.toEntity(super.getOne(id)));
    }

    public Page<ServerDTO> findServers(ServerSearchDTO serverSearchDTO, Pageable pageable) {
        Page<Server> serverPage = repository.searchServers(serverSearchDTO.getServerName(),
                serverSearchDTO.getServerDiscordID(),
                pageable);
        List<ServerDTO> result = serverMapper.toDTOs(serverPage.getContent());
        return new PageImpl<>(result, pageable, serverPage.getTotalElements());
    }

    public Page<ServerVideoHistoryDTO> listAllHistory(Pageable pageable, Long id){
        Server server = repository.getById(id);
        Page<ServerVideoHistory> serverVideoHistoryPage = serverVideoHistoryRepository.getServerVideoHistoriesByServer(server, pageable);
        List<ServerVideoHistoryDTO> result = serverVideoHistoryMapper.toDTOs(serverVideoHistoryPage.getContent());
        return new PageImpl<>(result, pageable, serverVideoHistoryPage.getTotalElements());
    }

    public ServerDTO getServerByDiscordID(String serverDiscordID) {
        return serverMapper.toDTO(repository.findByServerDiscordID(serverDiscordID));
    }
}
