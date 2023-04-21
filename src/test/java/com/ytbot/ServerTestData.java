package com.ytbot;

import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.ServerSearchDTO;
import com.ytbot.website.dto.ServerVideoHistoryDTO;
import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ServerTestData {
    ServerDTO serverDTO1 = new ServerDTO("name1",
            "dId1",
            new HashSet<>(),
            new HashSet<>());
    ServerDTO serverDTO2 = new ServerDTO("name2",
            "dId2",
            new HashSet<>(),
            new HashSet<>());
    ServerDTO serverDTO3 = new ServerDTO("name3",
            "dId3",
            new HashSet<>(),
            new HashSet<>());


    List<ServerDTO> server_dto_list = Arrays.asList(serverDTO1, serverDTO2, serverDTO3);
    Set<ServerDTO> server_dto_set = Set.of(serverDTO1, serverDTO2, serverDTO3);

    Server server1 = new Server("name1",
            "dId1",
            null,
            null);

    Server server2 = new Server("name2",
            "dId2",
            null,
            null);
    Server server3 = new Server("name3",
            "dId3",
            null,
            null);


    List<Server> server_list = Arrays.asList(server1, server2, server3);

    ServerSearchDTO serverSearchDTO = new ServerSearchDTO("name1", "dId1");
    ServerVideoHistory serverVideoHistory1 = new ServerVideoHistory(server1, VideoTestData.video1, LocalDateTime.of(2023, 06, 04, 23, 49, 1, 1));
    ServerVideoHistory serverVideoHistory2 = new ServerVideoHistory(server2, VideoTestData.video2, LocalDateTime.of(2022, 07, 04, 21, 49, 1, 1));

    List<ServerVideoHistory> server_history_list = Arrays.asList(serverVideoHistory1, serverVideoHistory2);


    ServerVideoHistoryDTO serverVideoHistoryDto1 = new ServerVideoHistoryDTO(server1.getId(), VideoTestData.video1.getId(),"23:49:01 06.04.2023", VideoTestData.videoDTO1);
    ServerVideoHistoryDTO serverVideoHistoryDto2 = new ServerVideoHistoryDTO(server2.getId(), VideoTestData.video2.getId(), "21:49:01 07.04.2022", VideoTestData.videoDTO2);

    List<ServerVideoHistoryDTO> server_history_DTO_list = Arrays.asList(serverVideoHistoryDto1, serverVideoHistoryDto2);
}
