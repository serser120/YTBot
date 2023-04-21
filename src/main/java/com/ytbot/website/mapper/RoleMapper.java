package com.ytbot.website.mapper;

import com.ytbot.website.dto.RoleDTO;
import com.ytbot.website.model.Role;
import com.ytbot.website.repository.ServerVideoHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends GenericMapper<Role, RoleDTO> {

    protected RoleMapper(ModelMapper modelMapper,
                          ServerVideoHistoryRepository serverVideoHistoryRepository){
        super(modelMapper, Role.class, RoleDTO.class);
    }


    @Override
    public void mapSpecificFields(RoleDTO source, Role destination) {

    }

    @Override
    public void mapSpecificFields(Role source, RoleDTO destination) {

    }
}