package com.ytbot.website.mvc_controller;

import com.ytbot.website.dto.GenericDTO;
import com.ytbot.website.mapper.GenericMapper;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.repository.GenericRepository;
import com.ytbot.website.service.GenericService;
import com.ytbot.website.service.userdetails.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class GenericMVCControllerTest<E extends GenericModel, D extends GenericDTO> {
    protected GenericService<E, D> service;
    protected GenericMapper<E, D> mapper;

    @BeforeEach
    void init() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(CustomUserDetails
                .builder()
                .username("USER"),
                null,
                null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected abstract void index();
}
