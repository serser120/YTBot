package com.ytbot.website.service;

import com.ytbot.website.dto.ServerVideoHistoryDTO;
import com.ytbot.website.mapper.ServerVideoHistoryMapper;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class ServerVideoHistoryService extends GenericService<ServerVideoHistory, ServerVideoHistoryDTO> {
    private ServerVideoHistoryRepository serverVideoHistoryRepository;
    public ServerVideoHistoryService(ServerVideoHistoryRepository serverVideoHistoryRepository,
                                  ServerVideoHistoryMapper serverVideoHistoryMapper) {
        super(serverVideoHistoryRepository, serverVideoHistoryMapper);
        this.serverVideoHistoryRepository = serverVideoHistoryRepository;
    }



}
