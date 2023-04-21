package com.ytbot.website.repository;

import com.ytbot.website.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends GenericRepository<Video> {

    @Query(nativeQuery = true, value = """
            select * from videos 
            where videos.title ilike '%'|| coalesce(:title, '%') || '%'
            and videos.url ilike '%'|| coalesce(:url, '%') || '%'
            """)
    Page<Video> searchVideos(@Param(value = "title") String title, @Param(value = "url") String url, Pageable pageable);

}
