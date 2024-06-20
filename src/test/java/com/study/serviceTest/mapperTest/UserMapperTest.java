package com.study.serviceTest.mapperTest;

import com.study.domain.User;
import com.study.service.dto.UserDTO;
import com.study.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * This class contains unit tests for the {@link UserMapper} class.
 * The tests cover various operations such as converting entities to DTOs,
 * converting DTOs to entities, and handling optional and list conversions.
 */
public class UserMapperTest {

    private final String TEST_NAME_USER_1 = "Олександр";
    private final String TEST_NAME_USER_2 = "Андрій";


    private final int ID_1 = 1;
    private final int ID_3 = 3;

    private UserMapper userMapper;

    private UserDTO userDTO;
    private User user;

    private UserDTO createDTO(){
        return new UserDTO();
    }

    private UserDTO createDTO(String name){
        return new UserDTO().firstName(name);
    }

    private UserDTO createDTO(int id, String name){
        return new UserDTO().id(id).firstName(name);
    }

    private User createEntity(){
        return new User();
    }

    private User createEntity(String name){
        return new User().firstName(name);
    }

    private User createEntity(int id, String name){
        return new User().id(id).firstName(name);
    }

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();

        userDTO = new UserDTO().firstName(TEST_NAME_USER_2);
        user = new User().firstName(TEST_NAME_USER_1);
    }

    @Test
    void testToDTO() {
        assertEquals(userMapper.toDTO(user), createDTO(TEST_NAME_USER_1));
        assertEquals(userMapper.toDTO(createEntity()), createDTO());
        assertInstanceOf(UserDTO.class, userMapper.toDTO(createEntity()));
    }

    @Test
    void testToDTOs() {
        User user2 = createEntity(ID_3, TEST_NAME_USER_1);
        List<User> users = List.of(user, user2);
        List<UserDTO> expectedDTOs = List.of(createDTO(TEST_NAME_USER_1), createDTO(ID_3, TEST_NAME_USER_1));
        assertEquals(userMapper.toDTO(users), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<User> user = Optional.of(createEntity(ID_1, TEST_NAME_USER_2));
        assertEquals(userMapper.toDTO(user), Optional.of(createDTO().id(ID_1)));
    }

    @Test
    void testToEntity() {
        assertEquals(userMapper.toEntity(userDTO), createEntity(TEST_NAME_USER_2));
        assertInstanceOf(User.class, userMapper.toEntity(createDTO()));
    }

    @Test
    void testToEntities() {
        UserDTO user2DTO = createDTO(ID_3, TEST_NAME_USER_2);
        List<UserDTO> usersDTO = List.of(userDTO, user2DTO);
        List<User> expectedEntities = List.of(createEntity(TEST_NAME_USER_2), createEntity(ID_3, TEST_NAME_USER_2));
        assertEquals(userMapper.toEntity(usersDTO), expectedEntities);
    }
}
