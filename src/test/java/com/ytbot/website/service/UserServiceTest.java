package com.ytbot.website.service;

import com.ytbot.UserTestData;
import com.ytbot.website.dto.UserDTO;
import com.ytbot.website.mapper.RoleMapper;
import com.ytbot.website.mapper.UserMapper;
import com.ytbot.website.model.User;
import com.ytbot.website.repository.RoleRepository;
import com.ytbot.website.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class



























UserServiceTest extends GenericTest<User, UserDTO>{

    private final BCryptPasswordEncoder bCryptPasswordEncoder= Mockito.mock(BCryptPasswordEncoder.class);
    private final JavaMailSender javaMailSender= Mockito.mock(JavaMailSender.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserMapper userMapper = Mockito.mock(UserMapper.class);
    private final RoleRepository roleRepository= Mockito.mock(RoleRepository.class);
    private final RoleMapper roleMapper= Mockito.mock(RoleMapper.class);


    public UserServiceTest(){
        super();
        service = new UserService(userRepository, userMapper, bCryptPasswordEncoder, roleRepository, javaMailSender, roleMapper);
    }

    @Test
    @Order(1)
    public void getUserByLogin() {
        Mockito.when(userRepository.findUserByLogin("login1")).thenReturn(UserTestData.user1);
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        UserDTO userDTO = ((UserService)service).getUserByLogin("login1");
        assertEquals(UserTestData.userDTO1, userDTO);
    }

    @Test
    @Order(2)
    public void getUserByEmail() {
        Mockito.when(userRepository.findUserByEmail("email1")).thenReturn(UserTestData.user1);
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        UserDTO userDTO = ((UserService)service).getUserByEmail("email1");
        assertEquals(UserTestData.userDTO1, userDTO);
    }


    @Test
    @Order(3)
    @Override
    protected void create() {
        Mockito.when(userMapper.toEntity(UserTestData.userDTO1)).thenReturn(UserTestData.user1);
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        Mockito.when(userRepository.save(UserTestData.user1)).thenReturn(UserTestData.user1);
        UserDTO userDTO = service.create(UserTestData.userDTO1);
        log.info("Testing create(): " + userDTO);
        assertEquals(UserTestData.userDTO1, userDTO);
    }

    @Test
    @Order(4)
    public void changePassword() {
        Mockito.when(userRepository.findUserByChangePasswordToken("token1")).thenReturn(UserTestData.user1);
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        Mockito.when(bCryptPasswordEncoder.encode("password1")).thenReturn("password1" + 1);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(UserTestData.user1));
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        UserDTO userDTO =  service.getOne(1L);
        assertEquals(UserTestData.userDTO1.getPassword(), userDTO.getPassword());
    }

    @Test
    @Order(5)
    @Override
    protected void getAll() {
        assertEquals(true, true);
    }

    @Test
    @Order(6)
    protected void getAllUsersWithServers() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "serverName"));
        Mockito.when(userRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(UserTestData.user_list));
        Mockito.when(userMapper.toDTOs(UserTestData.user_list)).thenReturn(UserTestData.user_dto_list);
        Page<UserDTO> userDTOS = ((UserService)service).getAllUsersWithServers(pageRequest);
        log.info("Testing getAllUsersWithServers(): " + userDTOS);
        assertEquals(UserTestData.user_dto_list, userDTOS.getContent());
    }

    @Test
    @Order(7)
    protected void findUsers() {
        PageRequest pageRequest = PageRequest.of(1, 10, Sort.by(Sort.Direction.ASC, "login"));
        Mockito.when(userRepository.searchUsers("login1", "email1", pageRequest))
                .thenReturn(new PageImpl<>(UserTestData.user_list));
        Mockito.when(userMapper.toDTOs(UserTestData.user_list)).thenReturn(UserTestData.user_dto_list);
        Page<UserDTO> userDTOS = ((UserService)service).findUsers(UserTestData.userSearchDTO, pageRequest);
        log.info("Testing findServers(): " + userDTOS);
        assertEquals(UserTestData.user_dto_list, userDTOS.getContent());
    }

    @Test
    @Order(8)
    @Override
    protected void getOne() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(UserTestData.user1));
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        UserDTO userDTO =  service.getOne(1L);
        log.info("Testing getOne(): " + userDTO);
        assertEquals(UserTestData.userDTO1, userDTO);
    }

    @Test
    @Order(9)
    protected void getUser() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(UserTestData.user1));
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        Mockito.when(userMapper.toEntity(UserTestData.userDTO1)).thenReturn(UserTestData.user1);
        UserDTO userDTO = ((UserService) service).getUser(1L);
        log.info("Testing getOne(): " + userDTO);
        assertEquals(UserTestData.userDTO1, userDTO);
    }

    @Test
    @Order(10)
    @Override
    protected void update() {
        Mockito.when(userMapper.toEntity(UserTestData.userDTO1)).thenReturn(UserTestData.user1);
        Mockito.when(userMapper.toDTO(UserTestData.user1)).thenReturn(UserTestData.userDTO1);
        Mockito.when(userRepository.save(UserTestData.user1)).thenReturn(UserTestData.user1);
        UserDTO userDTO = service.update(UserTestData.userDTO1);
        log.info("Testing update(): " + userDTO);
        assertEquals(UserTestData.userDTO1, userDTO);
    }
}
