package com.ytbot;

import com.ytbot.bot.JMusicBot;
import com.ytbot.website.botReader.DBConfigContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class YTBotApplication
        implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(YTBotApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        JMusicBot.mainMethod(args);
    }
}
