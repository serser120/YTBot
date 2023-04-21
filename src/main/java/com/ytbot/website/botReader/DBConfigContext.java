package com.ytbot.website.botReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.ytbot.website.constants.DBConsts.*;

@Configuration
@ComponentScan
public class DBConfigContext {
    @Bean
    @Scope("singleton")
    public Connection newConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + PORT + "/" + DB,
                USER, PASSWORD);
    }
    @Bean
    public ServerAndVideoBean serverAndVideoBean() throws SQLException{
        return new ServerAndVideoBean(newConnection());
    }
}
