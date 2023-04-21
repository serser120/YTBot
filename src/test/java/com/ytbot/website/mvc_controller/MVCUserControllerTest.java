package com.ytbot.website.mvc_controller;

import com.ytbot.ServerTestData;
import com.ytbot.UserTestData;
import com.ytbot.VideoTestData;
import com.ytbot.website.controller.mvc.MVCUserController;
import com.ytbot.website.controller.mvc.MVCVideoController;
import com.ytbot.website.dto.*;
import com.ytbot.website.service.ServerService;
import com.ytbot.website.service.UserService;
import com.ytbot.website.service.VideoService;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class MVCUserControllerTest extends CommonControllerTestMVC {

    @Test
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void listAll() throws Exception {
        mvc.perform(get("/users")
                        .param("page", "1")
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewAllUsers"))
                .andExpect(model().attributeExists("users"))
                .andReturn();
    }

    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void getOne() throws Exception {
        mvc.perform(get("/users/1")
                        .param("id", "1L")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewUser"))
                .andExpect(model().attributeExists("servers"))
                .andExpect(model().attributeExists("user"))
                .andReturn();
    }



    //Работает только при удалении из контроллера проверки юзера
    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void searchUsers() throws Exception {
        mvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("page", "1")
                        .param("size", "5")
                        .flashAttr("userSearchForm", UserTestData.userSearchDTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users/viewAllUsers"))
                .andExpect(model().attributeExists("users"))
                .andReturn();
    }

    @Test
    @Order(4)
    @WithAnonymousUser
    protected void registration() throws Exception {
        mvc.perform(get("/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .flashAttr("userForm", UserTestData.userDTO1)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("http://localhost/login"));
    }

    @Test
    @Order(5)
    @WithAnonymousUser
    protected void rememberPassword() throws Exception {
        mvc.perform(get("/remember-password")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .flashAttr("changePasswordForm", UserTestData.userDTO1)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("http://localhost/login"));
    }

    @Test
    @Order(6)
    @WithAnonymousUser
    protected void changePassword() throws Exception {
        mvc.perform(get("/remember-password")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("uuid", "123")
                        .flashAttr("changePasswordForm", UserTestData.userDTO1)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("http://localhost/login"));
    }

    @Test
    @Order(7)
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin")
    protected void addServer() throws Exception {
        ServerDTO serverDTO = new ServerDTO("Тестовая песочница", "1079412334373650494", new HashSet<>(), new HashSet<>());
        mvc.perform(post("/users/addServer/1")
                        .flashAttr("serverForm", serverDTO)
                        .flashAttr("id", 1L)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlTemplate("/users/1"));
    }


}
