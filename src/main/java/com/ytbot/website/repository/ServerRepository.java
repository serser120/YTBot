package com.ytbot.website.repository;

import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends GenericRepository<Server> {

    @Query(nativeQuery = true, value = """
            select * from servers 
            where servers.name ilike '%'|| coalesce(:name, '%') || '%'
            and servers.discord_id ilike '%'|| coalesce(:discord_id, '%') || '%'
            """)
    Page<Server> searchServers(@Param(value = "name") String name,
                           @Param(value = "discord_id") String discord_id,
                           Pageable pageable);

//    @Query(nativeQuery = true, value = """
//            select video_id, playback_date from server_video_history where id = 1
//            """)
//    Page<ServerVideoHistory> getServerVideoHistory(Pageable pageable, @Param(value = "name") Server server);

    Server findByServerDiscordID(String serverDiscordID);

}
