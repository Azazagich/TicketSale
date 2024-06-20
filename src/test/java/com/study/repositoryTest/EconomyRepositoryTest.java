package com.study.repositoryTest;

import com.study.domain.Economy;
import com.study.repository.EconomyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class contains unit tests for the {@link EconomyRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link Economy} entities in the repository.
 * */
public class EconomyRepositoryTest {
    private final String ECONOMY_CLASS_COMFORT = "Комфорт";
    private final String ECONOMY_CLASS_STANDARD = "Стандарт";
    private final String ECONOMY_CLASS_ECONOMY = "Економ";

    private Economy economy1;
    private Economy economy2;
    private Economy economy3;

    private static EconomyRepository economyRepository;

    private Economy createEntity(String type) {
        return new Economy().type(type);
    }

    @BeforeEach
    void setUp() {
        economyRepository = new EconomyRepository();

        // Initialize the repository and the Economy classes
        economy1 = createEntity(ECONOMY_CLASS_COMFORT);
        economy2 = createEntity(ECONOMY_CLASS_STANDARD);
        economy3 = createEntity(ECONOMY_CLASS_ECONOMY);

        // Add the Economy classes to a list and save them in the repository
        List<Economy> economies = new ArrayList<>();
        economies.add(economy1);
        economies.add(economy2);
        economies.add(economy3);

        economyRepository.saveAll(economies);
    }

    @AfterEach
    void tearDown() {
        economyRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedEconomy() {
        // Check if the Economy with ID 1 is correctly found
        assertTrue( economyRepository.existById(economy1.getId()));
        assertEquals(economy1, economyRepository.findById(economy1.getId()).get());

        // Check if the Economy with ID 2 is correctly found and has the correct type
        assertTrue( economyRepository.existById(economy2.getId()));
        assertEquals(economy2.getType(), economyRepository.findById(economy2.getId()).get().getType());

        // Check if the Economy with ID 2 is the same object as economy2
        assertEquals(economy2, economyRepository.findById(economy2.getId()).get());

        // Ensure that Economy with ID 3 is not the same as economy2
        assertTrue( economyRepository.existById(economy3.getId()));
        assertNotEquals(economy3, economyRepository.findById(economy2.getId()).get());

        // Ensure that Economy with ID 1 is not the same as economy3
        assertNotEquals(economy3, economyRepository.findById(economy1.getId()).get());
    }


    @Test
    void findAll_thenReturnAllEntities(){
        assertEquals(economyRepository.findAll(), List.of(economy1, economy2, economy3));
    }


    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(economyRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(economyRepository.existById(economy1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(economyRepository.existById(economy2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(economyRepository.existById(economy3.getId()));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){

        Economy economyUpdate = new Economy().type(ECONOMY_CLASS_ECONOMY).id(economy2.getId());


        // Update the Economy with ID 2 and check if the update was successful
        assertTrue(economyRepository.updateId(economy2.getId(), economyUpdate));

        // Ensure that updating with a null Economy returns false
        assertFalse(economyRepository.updateId(economy1.getId(), null));

        // Ensure that updating with null ID and null Economy returns false
        assertFalse(economyRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid Economy returns false
        assertFalse(economyRepository.updateId(null, economyUpdate));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        economyRepository.existById(economy2.getId());
        economyRepository.deleteById(economy2.getId());

        // Check that the element with ID 2 no longer exists
        economyRepository.existById(economy2.getId());
        assertFalse(economyRepository.existById(economy2.getId()));
    }


    @Test
    public void givenEconomy_thenDeleteElInMap(){
        // Delete the economy3 element from the repository
        assertTrue(economyRepository.existById(economy3.getId()));
        economyRepository.delete(economy3);


        // Verify that the element with ID 3 no longer exists
        assertFalse(economyRepository.existById(economy3.getId()));
    }


    @Test
    public void deleteAllElInMap(){
        // Verify that all elements exist before deletion
        assertTrue(economyRepository.existById(economy3.getId()));
        assertTrue(economyRepository.existById(economy2.getId()));
        assertTrue(economyRepository.existById(economy1.getId()));

        // Delete all elements from the repository
        economyRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(economyRepository.existById(economy3.getId()));
        assertFalse(economyRepository.existById(economy2.getId()));
        assertFalse(economyRepository.existById(economy1.getId()));
    }

    @Test
    public void givenListEconomy_thenDeleteListElInMap(){
        // Create a list of elements to delete
        List<Economy> economyDelete = new ArrayList<>();
        economyDelete.add(economy1);
        economyDelete.add(economy2);

        // Delete the list of elements from the repository
        economyRepository.deleteAll(economyDelete);

        // Verify that the element with ID 3 still exists
        assertTrue(economyRepository.existById(economy3.getId()));

        // Verify that the elements with IDs 1 and 2 no longer exist
        assertFalse(economyRepository.existById(economy2.getId()));
        assertFalse(economyRepository.existById(economy1.getId()));
    }

    @Test
    public void givenElEconomy_whenSaveElInMap_thenReturnEconomy(){
        Economy economy4 = createEntity(ECONOMY_CLASS_ECONOMY);

        // Save the new economy in the repository
        economyRepository.save(economy4);
        economyRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(economyRepository.existById(economy4.getId()));
    }

    @Test
    public void givenListElEconomy_whenSaveListElInMap_thenReturnListElEconomy(){
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(economyRepository.existById(economy1.getId()));
        assertTrue(economyRepository.existById(economy2.getId()));

        Economy economy4 = createEntity(ECONOMY_CLASS_STANDARD);

        // Verify that the element with ID 4 does not exist before saving
        assertFalse(economyRepository.existById(economy4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(economyRepository.findById(economy1.getId()).get(), economy1);
        assertEquals(economyRepository.findById(economy2.getId()).get(), economy2);
    }
}
