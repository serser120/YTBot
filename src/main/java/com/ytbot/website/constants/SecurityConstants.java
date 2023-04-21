package com.ytbot.website.constants;

import java.util.List;

public interface SecurityConstants {
    final List<String> RESOURCES_WHITE_LIST = List.of("/resources/**",
            "/js/**",
            "/css/**",
            "/swagger-ui/**",
            "/",
            "/error",
            "/webjars/bootstrap/5.0.2/**",
            "/images/**");

    final List<String> SERVERS_WHITE_LIST = List.of("/servers",
            "/videos/search",
            "/videos/{id}");
    final List<String> VIDEOS_WHITE_LIST = List.of("/videos",
            "/videos/search",
            "/videos/{id}");
    final List<String> SERVERS_PERMISSION_LIST = List.of("/servers/add",
            "/servers/update",
            "/servers/delete");

    final List<String> USERS_WHITE_LIST = List.of("/login",
            "/users/registration",
            "/users/remember-password",
            "/users/change-password");
}
