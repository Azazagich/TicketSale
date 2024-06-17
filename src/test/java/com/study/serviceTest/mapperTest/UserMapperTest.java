package com.study.serviceTest.mapperTest;

import com.study.domain.Train;
import com.study.domain.User;
import com.study.service.dto.TrainDTO;
import com.study.service.dto.UserDTO;
import com.study.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Test class for {@link UserMapper}.
 * This class contains unit tests for the {@link UserMapper} class which converts
 * between {@link User} and {@link UserDTO} objects.
 */
public class UserMapperTest {

    private UserMapper userMapper;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();

        userDTO = new UserDTO().firstName("Андрій");
        user = new User().firstName("Олександр");
    }

    @Test
    void testToDTO() {
        assertEquals(userMapper.toDTO(user), new UserDTO().firstName("Олександр"));
        assertEquals(userMapper.toDTO(new User()), new UserDTO());
        assertInstanceOf(UserDTO.class, userMapper.toDTO(new User()));
    }

    @Test
    void testToDTOs() {
        User user2 = new User().id(3).firstName("Олександр");
        List<User> users = List.of(user, user2);
        List<UserDTO> expectedDTOs = List.of(new UserDTO().firstName("Олександр"), new UserDTO().id(3).firstName("Олександр"));
        assertEquals(userMapper.toDTO(users), expectedDTOs);
    }

    @Test
    void testToOptionalDTO() {
        Optional<User> user = Optional.of(new User().id(1).firstName("Андрій"));
        assertEquals(userMapper.toDTO(user), Optional.of(new UserDTO().id(1)));
    }

    @Test
    void testToEntity() {
        assertEquals(userMapper.toEntity(userDTO), new User().firstName("Андрій"));
        assertInstanceOf(User.class, userMapper.toEntity(new UserDTO()));
    }

    @Test
    void testToEntities() {
        UserDTO user2DTO = new UserDTO().id(3).firstName("Андрій");
        List<UserDTO> usersDTO = List.of(userDTO, user2DTO);
        List<User> expectedEntities = List.of(new User().firstName("Андрій"), new User().id(3).firstName("Андрій"));
        assertEquals(userMapper.toEntity(usersDTO), expectedEntities);
    }
}
