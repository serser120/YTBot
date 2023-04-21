package com.ytbot.website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "servers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "server_seq", allocationSize = 1)
public class Server extends GenericModel {

    @Column(name = "name")
    private String serverName;

    @Column(name = "discord_id")
    private String serverDiscordID;

    @OneToMany(mappedBy = "server", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ServerVideoHistory> serverVideoHistories;

    @ManyToMany
    @JoinTable(name = "users_servers",
            joinColumns = @JoinColumn(name = "server_id"), foreignKey = @ForeignKey(name = "FK_SERVERS_USERS"),
            inverseJoinColumns = @JoinColumn(name = "user_id"), inverseForeignKey = @ForeignKey(name = "FK_USERS_SERVERS"))
    private Set<User> users;
}
