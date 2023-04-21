package com.ytbot.website.mvc_controller;

import com.ytbot.website.dto.VideoSearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class MVCVideoControllerTest extends CommonControllerTestMVC {

    @Test
    @Order(1)
    @WithAnonymousUser
    protected void listAll() throws Exception {
        mvc.perform(get("/videos")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("videos/viewAllVideos"))
                .andExpect(model().attributeExists("videos"))
                .andReturn();
    }

    @Test
    @Order(2)
    @WithAnonymousUser
    protected void getOne() throws Exception {
        mvc.perform(get("/videos/1")
                        .param("page", "1")
                        .param("size", "5")
                        .param("id", "1L")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("videos/viewVideo"))
                .andExpect(model().attributeExists("video"))
                .andReturn();
    }


    @Test
    @Order(3)
    @WithAnonymousUser
    protected void searchVideos() throws Exception {
        VideoSearchDTO videoSearchDTO = new VideoSearchDTO("Mick Gordon  The Only Thing They Fear Is You  DOOM Eternal OST High Quality", "https://www.youtube.com/watch?v=EQmIBHObtCs");
        mvc.perform(get("/videos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "1")
                        .param("size", "5")
                        .flashAttr("videoSearchForm", videoSearchDTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("videos/viewAllVideos"))
                .andExpect(model().attributeExists("videos"))
                .andReturn();
    }
}


