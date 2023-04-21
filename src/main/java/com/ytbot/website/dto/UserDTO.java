package com.ytbot.website.dto;

import com.ytbot.website.model.Role;;
import com.ytbot.website.model.Server;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends GenericDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String changePasswordToken;
    private RoleDTO role;
    private String roleDescription;
    private Set<Long> serversIds;
    private Set<ServerDTO> servers;
}
