package com.ytbot;

import com.ytbot.website.dto.*;
import com.ytbot.website.model.Role;
import com.ytbot.website.model.Server;
import com.ytbot.website.model.ServerVideoHistory;
import com.ytbot.website.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public interface UserTestData {
    UserDTO userDTO1 = new UserDTO("firstname1",
            "lastname1",
            "email1",
            "login1",
            "password1",
            "token1",
            new RoleDTO(),
            "role1",
            new HashSet<>(),
            new HashSet<>());

    UserDTO userDTO2 = new UserDTO("firstname2",
            "lastname2",
            "email2",
            "login2",
            "password2",
            "token2",
            new RoleDTO(),
            "role2",
            new HashSet<>(),
            new HashSet<>());

    UserDTO userDTO3 = new UserDTO("firstname3",
            "lastname3",
            "email3",
            "login3",
            "password3",
            "token3",
            new RoleDTO(),
            "role3",
            new HashSet<>(),
            new HashSet<>());

    List<UserDTO> user_dto_list = Arrays.asList(userDTO1, userDTO2, userDTO3);

    User user1 = new User("firstname1",
            "lastname1",
            "email1",
            "login1",
            "password1",
            "token1",
            new Role(),
            new HashSet<>());

    User user2 = new User("firstname2",
            "lastname2",
            "email2",
            "login2",
            "password2",
            "token2",
            new Role(),
            new HashSet<>());

    User user3 = new User("firstname3",
            "lastname3",
            "email3",
            "login3",
            "password3",
            "token3",
            new Role(),
            new HashSet<>());

    List<User> user_list = Arrays.asList(user1, user2, user3);

    UserSearchDTO userSearchDTO = new UserSearchDTO("login1", "email1");

}
