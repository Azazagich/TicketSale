package com.study.repositoryTests;

import com.study.domain.*;
import com.study.repository.AgeGroupRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the {@link AgeGroupRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link AgeGroup} entities in the repository.
 * */

public class AgeGroupRepositoryTest {

    private static AgeGroupRepository ageGroupRepository;

    private final String AGE_GROUP_ADULT_TYPE = "Дорослий";
    private final String AGE_GROUP_NAME_CHILD_TYPE = "Дитина";
    private final String AGE_GROUP_RETIREE_CHILD_TYPE = "Пенсіонер";

    private AgeGroup ageGroup1;
    private AgeGroup ageGroup2;
    private AgeGroup ageGroup3;

    private AgeGroup createEntity(String type) {
        return new AgeGroup().type(type);
    }

    @BeforeEach
    void setUp() {
        ageGroupRepository = new AgeGroupRepository();

        // Initialize the repository and the age groups
        ageGroup1 = createEntity(AGE_GROUP_ADULT_TYPE);
        ageGroup2 = createEntity(AGE_GROUP_NAME_CHILD_TYPE);
        ageGroup3 = createEntity(AGE_GROUP_RETIREE_CHILD_TYPE);

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
        assertTrue( ageGroupRepository.existById(ageGroup1.getId()));
        assertEquals(ageGroup1, ageGroupRepository.findById(ageGroup1.getId()).get());

        // Check if the AgeGroup with ID 2 is correctly found and has the correct type
        assertTrue( ageGroupRepository.existById(ageGroup2.getId()));
        assertEquals(ageGroup2.getType(), ageGroupRepository.findById(ageGroup2.getId()).get().getType());

        // Check if the AgeGroup with ID 2 is the same object as ageGroup2
        assertEquals(ageGroup2, ageGroupRepository.findById(ageGroup2.getId()).get());

        // Ensure that AgeGroup with ID 2 is not the same as ageGroup3
        assertTrue( ageGroupRepository.existById(ageGroup3.getId()));
        assertNotEquals(ageGroup3, ageGroupRepository.findById(ageGroup2.getId()).get());

        // Ensure that AgeGroup with ID 1 is not the same as ageGroup3
        assertNotEquals(ageGroup3, ageGroupRepository.findById(ageGroup1.getId()).get());
    }



    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(ageGroupRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(ageGroupRepository.existById(ageGroup1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(ageGroupRepository.existById(ageGroup2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(ageGroupRepository.existById(ageGroup3.getId()));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){

        AgeGroup ageGroupUpdate = new AgeGroup().type(AGE_GROUP_RETIREE_CHILD_TYPE).id(ageGroup2.getId());


        // Update the AgeGroup with ID 2 and check if the update was successful
       assertTrue(ageGroupRepository.updateId(ageGroup2.getId(), ageGroupUpdate));

        // Ensure that updating with a null AgeGroup returns false
       assertFalse(ageGroupRepository.updateId(ageGroup1.getId(), null));

        // Ensure that updating with null ID and null AgeGroup returns false
       assertFalse(ageGroupRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid AgeGroup returns false
       assertFalse(ageGroupRepository.updateId(null,ageGroupUpdate));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        ageGroupRepository.existById(ageGroup2.getId());
        ageGroupRepository.deleteById(ageGroup2.getId());

        // Check that the element with ID 2 no longer exists
        ageGroupRepository.existById(ageGroup2.getId());
        assertFalse(ageGroupRepository.existById(ageGroup2.getId()));
    }


    @Test
    public void givenAgeGroup_thenDeleteElInMap(){
        // Delete the ageGroup3 element from the repository
        assertTrue(ageGroupRepository.existById(ageGroup3.getId()));
        ageGroupRepository.delete(ageGroup3);


        // Verify that the element with ID 3 no longer exists
        assertFalse(ageGroupRepository.existById(ageGroup3.getId()));
     }


    @Test
    public void deleteAllElInMap(){
        assertTrue(ageGroupRepository.existById(ageGroup3.getId()));
        assertTrue(ageGroupRepository.existById(ageGroup2.getId()));
        assertTrue(ageGroupRepository.existById(ageGroup1.getId()));

        // Delete all elements from the repository
        ageGroupRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(ageGroupRepository.existById(ageGroup3.getId()));
        assertFalse(ageGroupRepository.existById(ageGroup2.getId()));
        assertFalse(ageGroupRepository.existById(ageGroup1.getId()));
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
        assertFalse(ageGroupRepository.existById(ageGroup2.getId()));
        assertFalse(ageGroupRepository.existById(ageGroup1.getId()));
    }

    @Test
    public void givenElAgeGroup_whenSaveElInMap_thenReturnAgeGroup(){
        AgeGroup ageGroup4 = createEntity(AGE_GROUP_ADULT_TYPE);

        ageGroupRepository.save(ageGroup4);
        ageGroupRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(ageGroupRepository.existById(ageGroup4.getId()));
    }

    @Test
    public void givenListElAgeGroup_whenSaveListElInMap_thenReturnListElAgeGroup(){
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(ageGroupRepository.existById(ageGroup1.getId()));
        assertTrue(ageGroupRepository.existById(ageGroup2.getId()));

        AgeGroup ageGroup4 = createEntity(AGE_GROUP_ADULT_TYPE);

        // Verify that the elements with IDs 4 do not exist
         assertFalse(ageGroupRepository.existById(ageGroup4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(ageGroupRepository.findById(ageGroup1.getId()).get(), ageGroup1);
        assertEquals(ageGroupRepository.findById(ageGroup2.getId()).get(), ageGroup2);
    }
}
