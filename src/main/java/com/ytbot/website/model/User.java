package com.ytbot.website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "uniqueEmail", columnNames = "email"),
                @UniqueConstraint(name = "uniqueLogin", columnNames = "login")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends GenericModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "change_password_token")
    private String changePasswordToken;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_ROLES"))
    private Role role;

    @ManyToMany
    @JoinTable(name = "users_servers",
            joinColumns = @JoinColumn(name = "user_id"), foreignKey = @ForeignKey(name = "FK_USERS_SERVERS"),
            inverseJoinColumns = @JoinColumn(name = "server_id"), inverseForeignKey = @ForeignKey(name = "FK_SERVERS_USERS"))
    private Set<Server> servers;
}
