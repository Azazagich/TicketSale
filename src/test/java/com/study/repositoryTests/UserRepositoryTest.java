package com.study.repositoryTests;

import com.study.domain.User;
import com.study.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the {@link UserRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link User} entities in the repository.
 */
public class UserRepositoryTest {
    private final String[] NAMES_USERS = {"Євген", "Олександр", "Петро"};

    private User user1;
    private User user2;
    private User user3;

    private static UserRepository userRepository;

    private User createEntity(String name) {
        return new User().firstName(name);
    }

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();

        // Initialize the repository and the age groups
        user1 = createEntity(NAMES_USERS[0]);
        user2 = createEntity(NAMES_USERS[1]);
        user3 = createEntity(NAMES_USERS[2]);

        // Add the age groups to a list and save them in the repository
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        userRepository.saveAll(users);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedUser() {
        // Check if the User with ID 1 is correctly found
        assertTrue(userRepository.existById(user1.getId()));
        assertEquals(user1, userRepository.findById(user1.getId()).get());

        // Check if the User with ID 2 is correctly found and has the correct type
        assertTrue(userRepository.existById(user2.getId()));
        assertEquals(user2.getFirstName(), userRepository.findById(user2.getId()).get().getFirstName());

        // Check if the User with ID 2 is the same object as user2
        assertEquals(user2, userRepository.findById(user2.getId()).get());

        // Ensure that User with ID 2 is not the same as user3
        assertTrue(userRepository.existById(user3.getId()));
        assertNotEquals(user3, userRepository.findById(user2.getId()).get());

        // Ensure that User with ID 1 is not the same as user3
        assertNotEquals(user3, userRepository.findById(user1.getId()).get());
    }


    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(userRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(userRepository.existById(user1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(userRepository.existById(user2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(userRepository.existById(user3.getId()));
    }

    
    @Test
    void findAll_thenReturnAllEntities(){
        assertTrue(userRepository.findAll().containsAll(List.of(user1, user2, user3)));
        assertEquals(userRepository.findAll(), List.of(user1, user2, user3));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){
        User userUpdate = new User().firstName(NAMES_USERS[1]).id(user2.getId());

        // Update the User with ID 2 and check if the update was successful
        assertTrue(userRepository.updateId(user2.getId(), userUpdate));

        // Ensure that updating with a null User returns false
        assertFalse(userRepository.updateId(user1.getId(), null));

        // Ensure that updating with null ID and null User returns false
        assertFalse(userRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid User returns false
        assertFalse(userRepository.updateId(null,userUpdate));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        userRepository.existById(user2.getId());
        userRepository.deleteById(user2.getId());

        // Check that the element with ID 2 no longer exists
        userRepository.existById(user2.getId());
        assertFalse(userRepository.existById(user2.getId()));
    }


    @Test
    public void givenUser_thenDeleteElInMap(){
        // Delete user3 from the repository
        assertTrue(userRepository.existById(user3.getId()));
        userRepository.delete(user3);

        // Verify that user3 no longer exists
        assertFalse(userRepository.existById(user3.getId()));
    }


    @Test
    public void deleteAllElInMap(){
        assertTrue(userRepository.existById(user3.getId()));
        assertTrue(userRepository.existById(user2.getId()));
        assertTrue(userRepository.existById(user1.getId()));

        // Delete all elements from the repository
        userRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(userRepository.existById(user3.getId()));
        assertFalse(userRepository.existById(user2.getId()));
        assertFalse(userRepository.existById(user1.getId()));
    }


    @Test
    public void givenListUser_thenDeleteListElInMap(){
        // Create a list of elements to delete
        List<User> userDelete = new ArrayList<>();
        userDelete.add(user1);
        userDelete.add(user2);

        // Delete the list of elements from the repository
        userRepository.deleteAll(userDelete);

        // Verify that the element with ID 3 still exists
        // Assertions.assertTrue(ageGroupRepository.existById(3));

        // Verify that the elements with IDs 1 and 2 no longer exist
        assertFalse(userRepository.existById(user2.getId()));
        assertFalse(userRepository.existById(user1.getId()));
    }


    @Test
    public void givenElUser_whenSaveElInMap_thenReturnUser(){
        User user4 = createEntity(NAMES_USERS[0]);

        userRepository.save(user4);
        userRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(userRepository.existById(user4.getId()));
    }


    @Test
    public void givenListElUser_whenSaveListElInMap_thenReturnListElUser(){
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(userRepository.existById(user1.getId()));
        assertTrue(userRepository.existById(user2.getId()));

        User user4 = createEntity(NAMES_USERS[1]);

        // Verify that the elements with IDs 4 do not exist
        assertFalse(userRepository.existById(user4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(userRepository.findById(user1.getId()).get(), user1);
        assertEquals(userRepository.findById(user2.getId()).get(), user2);
    }
}
