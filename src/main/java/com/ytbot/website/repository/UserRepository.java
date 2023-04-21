package com.ytbot.website.repository;

import com.ytbot.website.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends GenericRepository<User>{


    //select * from users where login = ?
//    @Query(nativeQuery = true, value = "select * from users where login = :login")
    User findUserByLogin(String login);

    User findUserByEmail(String email);

    User findUserByChangePasswordToken(String token);


    @Query(nativeQuery = true, value = """
            select * from users 
            where users.login ilike '%'|| coalesce(:login, '%') || '%'
            and users.email ilike '%'|| coalesce(:email, '%') || '%'
            """)
    Page<User> searchUsers(@Param(value = "login") String login,
                           @Param(value = "email") String email,
                           Pageable pageable);

    @Query(nativeQuery = true, value = """
            insert into users_servers (user_id, server_id) VALUES (:userId, :serverId)
            """)
    void addServerToUser(@Param(value = "userId") Long userId,
                         @Param(value = "serverId") Long serverId);
}

