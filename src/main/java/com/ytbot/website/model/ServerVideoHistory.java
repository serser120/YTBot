package com.ytbot.website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "server_video_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "server_video_history_seq", allocationSize = 1)
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class ServerVideoHistory extends GenericModel {
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "server_id", foreignKey = @ForeignKey(name = "FK_SERVER_VIDEO_HISTORY"))
    private Server server;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "video_id", foreignKey = @ForeignKey(name = "FK_VIDEO_SERVER_HISTORY"))
    private Video video;

    @Column(name = "playback_date")
    private LocalDateTime playbackDate;
}
