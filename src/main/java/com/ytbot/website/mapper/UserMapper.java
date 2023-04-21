package com.ytbot.website.mapper;

import com.ytbot.website.dto.UserDTO;
import com.ytbot.website.dto.UserSearchDTO;
import com.ytbot.website.model.GenericModel;
import com.ytbot.website.model.User;
import com.ytbot.website.repository.ServerRepository;
import com.ytbot.website.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper extends GenericMapper<User, UserDTO>{

    private final ServerRepository serverRepository;

    protected UserMapper(ModelMapper modelMapper,
                         ServerRepository serverRepository){
        super(modelMapper, User.class, UserDTO.class);
        this.serverRepository = serverRepository;
    }

    @Override
    public void mapSpecificFields(UserDTO source, User destination) {
        if (!Objects.isNull(source.getServersIds()) && source.getServersIds().size() > 0) {
            destination.setServers(new HashSet<>(serverRepository.findAllById(source.getServersIds())));
        } else {
            destination.setServers(Collections.emptySet());
        }
    }

    @Override
    public void mapSpecificFields(User source, UserDTO destination) {
        destination.setServersIds(getIds(source));
        destination.setRoleDescription(source.getRole().getDescription());
    }

    private Set<Long> getIds(User user) {
        return Objects.isNull(user) || Objects.isNull(user.getServers())
                ? null
                : user.getServers().stream().map(GenericModel::getId)
                .collect(Collectors.toSet());
    }
}
