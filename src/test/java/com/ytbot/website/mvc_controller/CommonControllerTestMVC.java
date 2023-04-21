package com.ytbot.website.mvc_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class CommonControllerTestMVC {
    @Autowired
    protected MockMvc mvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public void prepare() {
        objectMapper.registerModule(new JavaTimeModule());
    }

}
