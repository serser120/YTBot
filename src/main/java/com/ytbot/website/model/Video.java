package com.ytbot.website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "video_seq", allocationSize = 1)
public class Video extends GenericModel{

    @Column(name = "title", nullable = false)
    private String videoTitle;

    @Column(name = "length")
    private String videoLength;

    @Column(name = "url", nullable = false)
    private String videoUrl;

    @Column(name = "numberOfPlays")
    private int numberOfPlays;

    @Column(name = "youTubeIdentifier")
    private String youTubeIdentifier;

    @OneToMany(mappedBy = "video", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ServerVideoHistory> serverVideoHistories;

}
