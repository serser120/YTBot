package com.ytbot.website.repository;

import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerVideoHistoryRepository extends GenericRepository<ServerVideoHistory> {

//    @Query(nativeQuery = true, value = """
//            select * from server_video_history where server_video_history.server_id = :server
//            """)
    Page<ServerVideoHistory> getServerVideoHistoriesByServer(@Param(value = "server") Server server, Pageable pageable);
}
