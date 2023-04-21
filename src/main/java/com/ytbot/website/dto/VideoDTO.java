package com.ytbot.website.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoDTO extends GenericDTO{
    private String videoTitle;
    private String videoLength;
    private String videoUrl;
    private int numberOfPlays;
    private String youTubeIdentifier;
//    private Set<Long> serverVideoHistories;
}
