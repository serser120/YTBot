package com.ytbot;

import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.dto.VideoSearchDTO;
import com.ytbot.website.model.Video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface VideoTestData {
    VideoDTO videoDTO1 = new VideoDTO("title1",
            "length1",
            "url1",
            1,
            "identifier1");

    VideoDTO videoDTO2 = new VideoDTO("title2",
            "length2",
            "url2",
            2,
            "identifier2");

    VideoDTO videoDTO3 = new VideoDTO("title3",
            "length3",
            "url3",
            3,
            "identifier3");

    List<VideoDTO> VIDEO_DTO_LIST = Arrays.asList(videoDTO1, videoDTO2, videoDTO3);

    Video video1 = new Video("title1",
            "length1",
            "url1",
            1,
            "identifier1",
            null);

    Video video2 = new Video("title2",
            "length2",
            "url2",
            2,
            "identifier2",
            null);

    Video video3 = new Video("title3",
            "length3",
            "url3",
            3,
            "identifier3",
            null);

    List<Video> VIDEO_LIST = Arrays.asList(video1, video2, video3);

    VideoSearchDTO videoSearchDTO = new VideoSearchDTO("title1", "url1");
}
