package com.ytbot.website.service;

import com.ytbot.website.constants.MailConstants;
import com.ytbot.website.dto.*;
import com.ytbot.website.mapper.RoleMapper;
import com.ytbot.website.mapper.UserMapper;
import com.ytbot.website.model.Role;
import com.ytbot.website.model.User;
import com.ytbot.website.model.Video;
import com.ytbot.website.repository.RoleRepository;
import com.ytbot.website.repository.UserRepository;
import com.ytbot.website.utils.MailUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService extends GenericService<User, UserDTO> {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    protected UserService(UserRepository userRepository,
                          UserMapper userMapper,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository,
                          JavaMailSender javaMailSender,
                          RoleMapper roleMapper) {

        super(userRepository, userMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public UserDTO getUserByLogin(final String login) {
        return mapper.toDTO(((UserRepository) repository).findUserByLogin(login));
    }

    public UserDTO getUserByEmail(final String email) {
        return mapper.toDTO(((UserRepository) repository).findUserByEmail(email));
    }

    @Override
    public UserDTO create(UserDTO object) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(1L);
        roleDTO.setDescription("Роль пользователя");
        roleDTO.setTitle("USER");
        object.setRole(roleDTO);
        object.setPassword(bCryptPasswordEncoder.encode(object.getPassword()));
        return mapper.toDTO(repository.save(mapper.toEntity(object)));
    }

    public void sendChangePasswordEmail(final UserDTO userDTO) {
        UUID uuid = UUID.randomUUID();
        userDTO.setChangePasswordToken(uuid.toString());
        update(userDTO);
        SimpleMailMessage mailMessage = MailUtils.createEmailMessage(userDTO.getEmail(),
                MailConstants.MAIL_SUBJECT_FOR_REMEMBER_PASSWORD,
                MailConstants.MAIL_MESSAGE_FOR_REMEMBER_PASSWORD + uuid);
        javaMailSender.send(mailMessage);
    }

    public void changePassword(final String uuid,
                               final String password) {
        UserDTO user = userMapper.toDTO(((UserRepository) repository).findUserByChangePasswordToken(uuid));
        user.setChangePasswordToken(null);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        update(user);
    }

    public Page<UserDTO> getAllUsersWithServers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserDTO> userDTOList = userMapper.toDTOs(userPage.getContent());
        return new PageImpl<>(userDTOList, pageable, userPage.getTotalElements());
    }

//    public UserDTO getUserWithServers(Long id) {
//        return userMapper.toDTO(mapper.toEntity(super.getOne(id)));
//    }

    public Page<UserDTO> findUsers(UserSearchDTO userSearchDTO, Pageable pageable) {
        Page<User> userPage = userRepository.searchUsers(userSearchDTO.getLogin(),
                userSearchDTO.getEmail(),
                pageable);
        List<UserDTO> result = userMapper.toDTOs(userPage.getContent());
        return new PageImpl<>(result, pageable, userPage.getTotalElements());
    }

    public UserDTO getUser(Long id) {
        return mapper.toDTO(mapper.toEntity(super.getOne(id)));
    }

    public void sendChangeServerDTO(Long id, ServerDTO serverDTO) {
        UserDTO user = getOne(id);
        Set<ServerDTO> serverDTOS = user.getServers();
        serverDTOS.add(serverDTO);
        user.setServers(serverDTOS);
        Set<Long> ids = new HashSet<>();
        user.getServers().stream().forEach(serverDTO1 -> {
            ids.add(serverDTO1.getId());
        });
        user.setServersIds(ids);
        update(user);
    }

    public void editUser(UserDTO userDTO, Long id){
        UserDTO resUser = getUser(id);
        if (!userDTO.getFirstName().isEmpty()) resUser.setFirstName(userDTO.getFirstName());
        if (!userDTO.getLastName().isEmpty()) resUser.setLastName(userDTO.getLastName());
        if (!userDTO.getEmail().isEmpty()) resUser.setEmail(userDTO.getEmail());
        if (userDTO.getRoleDescription() != null )if (!userDTO.getRoleDescription().isEmpty())
            resUser.setRole(roleMapper.toDTO(roleRepository.findRoleByDescriptionContainingIgnoreCase(userDTO.getRoleDescription())));
        update(resUser);
    }

    public void deleteServerFromUser(Long userId, Long serverId){
        UserDTO resUser = getUser(userId);
//        Set<Long> newServerIds = new HashSet<>();
//        resUser.getServersIds().forEach(q ->{
//            if (q != serverId) newServerIds.add(q);
//        });
//        resUser.setServersIds(newServerIds);
        Set<ServerDTO> newServers= new HashSet<>();
        resUser.getServers().forEach(q ->{
            if (q.getId() != serverId) newServers.add(q);
        });
        resUser.setServers(newServers);
        update(resUser);
    }
}
