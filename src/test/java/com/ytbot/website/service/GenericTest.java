package com.ytbot.website.service;

import com.ytbot.website.service.userdetails.CustomUserDetails;
import com.ytbot.website.dto.GenericDTO;
import com.ytbot.website.mapper.GenericMapper;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.repository.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class GenericTest<E extends GenericModel, D extends GenericDTO> {
    protected GenericService<E, D> service;
    protected GenericRepository<E> repository;
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

    protected abstract void getAll();

    protected abstract void getOne();

    protected abstract void create();

    protected abstract void update();

}

