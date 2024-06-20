package com.study.service;

import com.study.repository.UserRepository;
import com.study.service.dto.UserDTO;
import com.study.service.mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the {@link UserService} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link UserDTO} entities using the service layer.
 */
public class UserServiceTest {
    
    private final int EXPECTED_SIZE_ADDITION = 1;
    private final int EXPECTED_SIZE_ADDITION_LIST = 2;

    private final int FIRST_ELEMENT_TRAIN = 0;
    private final int SECOND_ELEMENT_TRAIN = 1;
    private final int PRIMARY_LIST_TRAIN_SIZE = 3;

    private final String TEST_NAME_USER_1 = "Євген";
    private final String TEST_NAME_USER_2 = "Олександр";
    private final String TEST_NAME_USER_3 = "Петро";
    private final String TEST_NAME_USER_4 = "Сергій";

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

        userDTO1 = userService.save(createDTO(TEST_NAME_USER_1));
        userDTO2 = userService.save(createDTO(TEST_NAME_USER_2));
        userDTO3 = userService.save(createDTO(TEST_NAME_USER_3));
    }

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    void save() {
        int sizeBeforeSave = userService.findAll().size();
        UserDTO saved = userService.save(createDTO(TEST_NAME_USER_4));

        assertTrue(userService.existById(saved.getId()));

        UserDTO saved2 = userService.findById(saved.getId()).orElseThrow();

        assertEquals(TEST_NAME_USER_4, saved.getFirstName());
        assertEquals(saved2.getFirstName(), saved.getFirstName());
        assertEquals(sizeBeforeSave + EXPECTED_SIZE_ADDITION, userService.findAll().size());
    }

    @Test
    void saveAll() {
        int sizeBeforeSaveAll = userService.findAll().size();
        List<UserDTO> usersDTO = new ArrayList<>();

        UserDTO userDTO4 = createDTO(TEST_NAME_USER_4);
        UserDTO userDTO5 = createDTO(TEST_NAME_USER_3);

        usersDTO.add(userDTO4);
        usersDTO.add(userDTO5);

        List<UserDTO> userDTOS = userService.saveAll(usersDTO);

        assertTrue(userService.existById(userDTOS.get(FIRST_ELEMENT_TRAIN).getId()));
        assertTrue(userService.existById(userDTOS.get(SECOND_ELEMENT_TRAIN).getId()));
        assertEquals(userDTO4.getFirstName(), userDTOS.get(FIRST_ELEMENT_TRAIN).getFirstName());
        assertEquals(userDTO5.getFirstName(), userDTOS.get(SECOND_ELEMENT_TRAIN).getFirstName());
        assertEquals(sizeBeforeSaveAll + EXPECTED_SIZE_ADDITION_LIST, userService.findAll().size());
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
        int sizeBeforeUpdate = userService.findAll().size();
        UserDTO userDTO4 = createDTO(TEST_NAME_USER_4);

        assertTrue(userService.updateId(userDTO2.getId(), userDTO4));
        assertTrue(userService.existById(userDTO4.getId()));
        assertEquals(userDTO4.getFirstName(), userService.findById(userDTO4.getId()).get().getFirstName());
        assertEquals(sizeBeforeUpdate, userService.findAll().size());
    }

    @Test
    void delete() {
        int sizeBeforeDelete = userService.findAll().size();

        assertTrue(userService.existById(userDTO1.getId()));
        userService.delete(userDTO1);
        assertFalse(userService.existById(userDTO1.getId()));
        assertEquals(sizeBeforeDelete - EXPECTED_SIZE_ADDITION, userService.findAll().size());
    }

    @Test
    void deleteAll() {
        int sizeBeforeDeleteAll = userService.findAll().size();
        List<UserDTO> usersDTODelete = List.of(userDTO1, userDTO2);

        assertTrue(userService.existById(userDTO1.getId()));
        assertTrue(userService.existById(userDTO2.getId()));
        assertTrue(userService.existById(userDTO3.getId()));

        userService.deleteAll(usersDTODelete);
        assertFalse(userService.existById(userDTO1.getId()));
        assertFalse(userService.existById(userDTO2.getId()));
        assertTrue(userService.existById(userDTO3.getId()));
        assertEquals(sizeBeforeDeleteAll - EXPECTED_SIZE_ADDITION_LIST, userService.findAll().size());
    }
}
