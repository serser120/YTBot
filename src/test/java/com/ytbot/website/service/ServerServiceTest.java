package com.ytbot.website.service;

import com.ytbot.ServerTestData;
import com.ytbot.VideoTestData;
import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.ServerVideoHistoryDTO;
import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.mapper.ServerMapper;
import com.ytbot.website.mapper.ServerVideoHistoryMapper;
import com.ytbot.website.mapper.VideoMapper;
import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.model.Video;
import com.ytbot.website.repository.ServerRepository;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class ServerServiceTest extends GenericTest<Server, ServerDTO>{

    private final ServerRepository serverRepository = Mockito.mock(ServerRepository.class);
    private final ServerMapper serverMapper = Mockito.mock(ServerMapper.class);
    public final ServerVideoHistoryMapper serverVideoHistoryMapper = Mockito.mock(ServerVideoHistoryMapper.class);
    public final ServerVideoHistoryRepository serverVideoHistoryRepository = Mockito.mock(ServerVideoHistoryRepository.class);

    public ServerServiceTest(){
        super();
        service = new ServerService(serverRepository, serverMapper, serverVideoHistoryMapper, serverVideoHistoryRepository);
    }

    @Test
    @Order(1)
    @Override
    protected void getAll() {
        Mockito.when(serverRepository.findAll()).thenReturn(ServerTestData.server_list);
        Mockito.when(serverMapper.toDTOs(ServerTestData.server_list)).thenReturn(ServerTestData.server_dto_list);
        List<ServerDTO> serverDTOS = service.listAll();
        assertEquals(ServerTestData.server_list.size(), serverDTOS.size());
    }

    @Test
    @Order(2)
    @Override
    protected void getOne() {
        Mockito.when(serverRepository.findById(1L)).thenReturn(Optional.of(ServerTestData.server1));
        Mockito.when(serverMapper.toDTO(ServerTestData.server1)).thenReturn(ServerTestData.serverDTO1);
        ServerDTO serverDTO = service.getOne(1L);
        log.info("Testing getOne(): " + serverDTO);
        assertEquals(ServerTestData.serverDTO1, serverDTO);
    }

    @Test
    @Order(3)
    @Override
    protected void create() {
        Mockito.when(serverMapper.toEntity(ServerTestData.serverDTO1)).thenReturn(ServerTestData.server1);
        Mockito.when(serverMapper.toDTO(ServerTestData.server1)).thenReturn(ServerTestData.serverDTO1);
        Mockito.when(serverRepository.save(ServerTestData.server1)).thenReturn(ServerTestData.server1);
        ServerDTO serverDTO = service.create(ServerTestData.serverDTO1);
        log.info("Testing create(): " + serverDTO);
        assertEquals(ServerTestData.serverDTO1, serverDTO);
    }

    @Test
    @Order(4)
    @Override
    protected void update() {
        Mockito.when(serverMapper.toEntity(ServerTestData.serverDTO1)).thenReturn(ServerTestData.server1);
        Mockito.when(serverMapper.toDTO(ServerTestData.server1)).thenReturn(ServerTestData.serverDTO1);
        Mockito.when(serverRepository.save(ServerTestData.server1)).thenReturn(ServerTestData.server1);
        ServerDTO serverDTO = service.update(ServerTestData.serverDTO1);
        log.info("Testing update(): " + serverDTO);
        assertEquals(ServerTestData.serverDTO1, serverDTO);
    }

    @Test
    @Order(5)
    protected void findServers() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "serverName"));
        Mockito.when(serverRepository.searchServers("name1", "dId1", pageRequest))
                .thenReturn(new PageImpl<>(ServerTestData.server_list));
        Mockito.when(serverMapper.toDTOs(ServerTestData.server_list)).thenReturn(ServerTestData.server_dto_list);
        Page<ServerDTO> serverDTOS = ((ServerService)service).findServers(ServerTestData.serverSearchDTO, pageRequest);
        log.info("Testing findServers(): " + serverDTOS);
        assertEquals(ServerTestData.server_dto_list, serverDTOS.getContent());
    }

    @Test
    @Order(6)
    protected void listAllHistory() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "serverName"));
        Mockito.when(serverRepository.getById(1L)).thenReturn(ServerTestData.server1);
        Page<ServerVideoHistory> serverVideoHistoryPage = new PageImpl<>(ServerTestData.server_history_list, pageRequest, 10);
        Mockito.when(serverVideoHistoryRepository.getServerVideoHistoriesByServer(ServerTestData.server1, pageRequest)).thenReturn(serverVideoHistoryPage);
        Mockito.when(serverVideoHistoryMapper.toDTOs(ServerTestData.server_history_list)).thenReturn(ServerTestData.server_history_DTO_list);
        Page<ServerVideoHistoryDTO> serverVideoHistoryDTOS = ((ServerService)service).listAllHistory(pageRequest, 1L);
        assertEquals(ServerTestData.server_history_DTO_list, serverVideoHistoryDTOS.getContent());
    }

    @Test
    @Order(7)
    protected void getServerByDiscordID(){
        Mockito.when(serverRepository.findByServerDiscordID("dId1")).thenReturn(ServerTestData.server1);
        Mockito.when(serverMapper.toDTO(ServerTestData.server1)).thenReturn(ServerTestData.serverDTO1);
        ServerDTO serverDTO = ((ServerService)service).getServerByDiscordID("dId1");
        log.info("Testing getServerByDiscordID(): " + serverDTO);
        assertEquals(ServerTestData.serverDTO1, serverDTO);
    }
}
