package com.ytbot.website.mvc_controller;

import com.ytbot.ServerTestData;
import com.ytbot.VideoTestData;
import com.ytbot.website.controller.mvc.MVCServerController;
import com.ytbot.website.controller.mvc.MVCVideoController;
import com.ytbot.website.dto.ServerDTO;
import com.ytbot.website.dto.ServerSearchDTO;
import com.ytbot.website.dto.VideoDTO;
import com.ytbot.website.dto.VideoSearchDTO;
import com.ytbot.website.service.ServerService;
import com.ytbot.website.service.ServerVideoHistoryService;
import com.ytbot.website.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class MVCServerControllerTest extends CommonControllerTestMVC {

    @Test
    @Order(1)
    @WithAnonymousUser
    protected void listAll() throws Exception {
        mvc.perform(get("/servers")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("servers/viewAllServers"))
                .andExpect(model().attributeExists("servers"))
                .andReturn();
    }

    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void getVideos() throws Exception {
        mvc.perform(get("/servers/1")
                        .param("page", "1")
                        .param("size", "5")
                        .param("id", "1L")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("servers/viewServer"))
                .andExpect(model().attributeExists("server"))
                .andExpect(model().attributeExists("histories"))
                .andReturn();
    }


    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void searchServers() throws Exception {
        ServerSearchDTO serverSearchDTO = new ServerSearchDTO("Test2", "1088837606177001505");
        mvc.perform(get("/servers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "1")
                        .param("size", "5")
                        .flashAttr("serverSearchForm", serverSearchDTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("servers/viewAllServers"))
                .andExpect(model().attributeExists("servers"))
                .andReturn();
    }
}
