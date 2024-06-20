package com.study.serviceTest;

import com.study.repository.UserRepository;
import com.study.service.UserService;
import com.study.service.dto.UserDTO;
import com.study.service.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link UserService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link UserDTO} entities using the service layer.
 */
public class UserServiceTest {

    private final String[] NAMES_USERS = {"Євген", "Олександр", "Петро", "Сергій"};

    private final int FIRST_ELEMENT_TRAIN = 0;
    private final int SECOND_ELEMENT_TRAIN = 1;
    private final int PRIMARY_LIST_TRAIN_SIZE = 3;

    private UserDTO userDTO1;
    private UserDTO userDTO2;
    private UserDTO userDTO3;

    private UserService userService;
    private UserMapper userMapper;
    private UserRepository userRepository;

    private UserDTO createDTO(String name){
        return new UserDTO().firstName(name);
    }

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userMapper = new UserMapper();

        userService = new UserService(userRepository, userMapper);

        userDTO1 = userService.save(createDTO(NAMES_USERS[0]));
        userDTO2 = userService.save(createDTO(NAMES_USERS[1]));
        userDTO3 = userService.save(createDTO(NAMES_USERS[2]));
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    void save() {
        UserDTO saved = userService.save(createDTO(NAMES_USERS[3]));

        assertTrue(userService.existById(saved.getId()));

        UserDTO saved2 = userService.findById(saved.getId()).orElseThrow();

        assertEquals(NAMES_USERS[3], saved.getFirstName());
        assertEquals(saved2.getFirstName(), saved.getFirstName());
    }

    @Test
    void saveAll() {
        List<UserDTO> usersDTO = new ArrayList<>();

        UserDTO userDTO4 = createDTO(NAMES_USERS[3]);
        UserDTO userDTO5 = createDTO(NAMES_USERS[2]);

        usersDTO.add(userDTO4);
        usersDTO.add(userDTO5);

        List<UserDTO> userDTOS = userService.saveAll(usersDTO);

        assertTrue(userService.existById(userDTOS.get(FIRST_ELEMENT_TRAIN).getId()));
        assertTrue(userService.existById(userDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(userDTO4.getFirstName(), userDTOS.get(FIRST_ELEMENT_TRAIN).getFirstName());
        assertEquals(userDTO5.getFirstName(), userDTOS.get(SECOND_ELEMENT_TRAIN).getFirstName());
    }

    @Test
    void findById() {
        assertTrue(userService.existById(userDTO1.getId()));
        UserDTO saved = userService.findById(userDTO1.getId()).orElseThrow();
        assertEquals(userDTO1.getFirstName(), saved.getFirstName());
    }

    @Test
    void findAll() {
        assertEquals(PRIMARY_LIST_TRAIN_SIZE, userService.findAll().size());
        assertTrue(userService.existById(userDTO2.getId()));
        assertTrue(userService.existById(userDTO3.getId()));
        assertNotNull(userService.findAll());
    }

    @Test
    void updateId() {
        UserDTO userDTO4 = createDTO(NAMES_USERS[3]);

        assertTrue(userService.updateId(userDTO2.getId(), userDTO4));
        assertTrue(userService.existById(userDTO4.getId()));
        assertEquals(userDTO4.getFirstName(), userService.findById(userDTO4.getId()).get().getFirstName());
    }

    @Test
    void delete() {
        assertTrue(userService.existById(userDTO1.getId()));
        userService.delete(userDTO1);
        assertFalse(userService.existById(userDTO1.getId()));
    }

    @Test
    void deleteAll() {
        List<UserDTO> usersDTODelete = List.of(userDTO1, userDTO2);

        assertTrue(userService.existById(userDTO1.getId()));
        assertTrue(userService.existById(userDTO2.getId()));
        assertTrue(userService.existById(userDTO3.getId()));

        userService.deleteAll(usersDTODelete);
        assertFalse(userService.existById(userDTO1.getId()));
        assertFalse(userService.existById(userDTO2.getId()));
        assertTrue(userService.existById(userDTO3.getId()));
    }
}
