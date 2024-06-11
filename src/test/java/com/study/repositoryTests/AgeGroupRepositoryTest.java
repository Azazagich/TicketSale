package com.study.repositoryTests;

import com.study.domain.*;
import com.study.repository.AgeGroupRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class contains unit tests for the {@link AgeGroupRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link AgeGroup} entities in the repository.
 * */

public class AgeGroupRepositoryTest {

    private static AgeGroupRepository ageGroupRepository;
    private AgeGroup ageGroup1;
    private AgeGroup ageGroup2;
    private AgeGroup ageGroup3;


     {
        ageGroupRepository = new AgeGroupRepository();

        // Initialize the repository and the age groups
        ageGroup1 = new AgeGroup().type("Дорослий");
        ageGroup2 = new AgeGroup().type("Дитина");
        ageGroup3 = new AgeGroup().type("Дитина");

        // Add the age groups to a list and save them in the repository
        List<AgeGroup> ageGroups = new ArrayList<>();
        ageGroups.add(ageGroup1);
        ageGroups.add(ageGroup2);
        ageGroups.add(ageGroup3);

        ageGroupRepository.saveAll(ageGroups);
    }

    @AfterAll
    public static void tearDown() {
        ageGroupRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedAgeGroup() {
        // Check if the AgeGroup with ID 1 is correctly found
        Assertions.assertEquals(ageGroup1, ageGroupRepository.findById(1).get());

        // Check if the AgeGroup with ID 2 is correctly found and has the correct type
        Assertions.assertEquals(ageGroup2.getType(), ageGroupRepository.findById(2).get().getType());

        // Check if the AgeGroup with ID 2 is the same object as ageGroup2
        Assertions.assertEquals(ageGroup2, ageGroupRepository.findById(2).get());

        // Ensure that AgeGroup with ID 2 is not the same as ageGroup3
        Assertions.assertNotEquals(ageGroup3, ageGroupRepository.findById(2).get());

        // Ensure that AgeGroup with ID 1 is not the same as ageGroup3
        Assertions.assertNotEquals(ageGroup3, ageGroupRepository.findById(1).get());
    }



    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        Assertions.assertFalse(ageGroupRepository.existById(null));

        // Check if existence check for ID 1 returns true
        Assertions.assertTrue(ageGroupRepository.existById(1));

        // Check if existence check for ID 2 returns true
        Assertions.assertTrue(ageGroupRepository.existById(2));

        // Check if existence check for ID 3 returns true
        Assertions.assertTrue(ageGroupRepository.existById(3));

        // Check if existence check for a non-existing ID 4 returns false
        Assertions.assertFalse(ageGroupRepository.existById(4));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){

        AgeGroup ageGroupUpdate = new AgeGroup().type("Пенсіонер").id(2);

        // Update the AgeGroup with ID 2 and check if the update was successful
       Assertions.assertTrue(ageGroupRepository.updateId(2, ageGroupUpdate));

        // Verify that the AgeGroup with ID 2 is correctly updated
       Assertions.assertEquals(ageGroupRepository.findById(2).get(), ageGroupUpdate);

        // Ensure that updating with a null AgeGroup returns false
       Assertions.assertFalse(ageGroupRepository.updateId(1,null));

        // Ensure that updating with null ID and null AgeGroup returns false
       Assertions.assertFalse(ageGroupRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid AgeGroup returns false
       Assertions.assertFalse(ageGroupRepository.updateId(null,ageGroupUpdate));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        ageGroupRepository.deleteById(2);

        // Attempt to delete a non-existing element with ID 5
        ageGroupRepository.deleteById(5);

        // Check that the element with ID 2 no longer exists
        Assertions.assertFalse(ageGroupRepository.existById(2));
    }


    @Test
    public void givenAgeGroup_thenDeleteElInMap(){
        // Delete the ageGroup3 element from the repository
        ageGroupRepository.delete(ageGroup3);

        // Verify that the element with ID 3 no longer exists
        Assertions.assertFalse(ageGroupRepository.existById(3));
    }


    @Test
    public void deleteAllElInMap(){
        // Delete all elements from the repository
        ageGroupRepository.deleteAll();

        // Verify that none of the elements exist anymore
        Assertions.assertFalse(ageGroupRepository.existById(3));
        Assertions.assertFalse(ageGroupRepository.existById(2));
        Assertions.assertFalse(ageGroupRepository.existById(1));
        Assertions.assertFalse(ageGroupRepository.existById(0));

        // Check that the repository is empty
        Assertions.assertEquals(ageGroupRepository.groups, new HashMap<>());
    }

    @Test
    public void givenListAgeGroup_thenDeleteListElInMap(){
        // Create a list of elements to delete
        List<AgeGroup> ageGroupDelete = new ArrayList<>();
        ageGroupDelete.add(ageGroup1);
        ageGroupDelete.add(ageGroup2);

        // Delete the list of elements from the repository
        ageGroupRepository.deleteAll(ageGroupDelete);

        // Verify that the element with ID 3 still exists
       // Assertions.assertTrue(ageGroupRepository.existById(3));

        // Verify that the elements with IDs 1 and 2 no longer exist
        Assertions.assertFalse(ageGroupRepository.existById(2));
        Assertions.assertFalse(ageGroupRepository.existById(1));
    }

    @Test
    public void givenElAgeGroup_whenSaveElInMap_thenReturnAgeGroup(){
        AgeGroup ageGroup4 = new AgeGroup()
                .type("Дорослий");

        ageGroupRepository.save(ageGroup4);
        ageGroupRepository.save(null);

        // Verify that the element with ID 4 exists
        Assertions.assertTrue(ageGroupRepository.existById(4));

        // Verify that the element with ID 5 does not exist
        Assertions.assertFalse(ageGroupRepository.existById(5));
    }

    @Test
    public void givenListElAgeGroup_whenSaveListElInMap_thenReturnListElAgeGroup(){
        // Verify that the elements with IDs 1 and 2 exist
        Assertions.assertTrue(ageGroupRepository.existById(1));
        Assertions.assertTrue(ageGroupRepository.existById(2));

        // Verify that the elements with IDs 0 and 4 do not exist
        Assertions.assertFalse(ageGroupRepository.existById(0));
        Assertions.assertFalse(ageGroupRepository.existById(4));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        Assertions.assertEquals(ageGroupRepository.findById(1).get(), ageGroup1);
        Assertions.assertEquals(ageGroupRepository.findById(2).get(), ageGroup2);
    }
}
