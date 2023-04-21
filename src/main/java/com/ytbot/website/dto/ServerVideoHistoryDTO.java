package com.ytbot.website.dto;


import com.ytbot.website.model.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerVideoHistoryDTO extends GenericDTO {
    private Long serverId;

    private Long videoId;

    private String playbackDate;

    private VideoDTO videoDTO;
}
