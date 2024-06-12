package com.study.repositoryTests;

import com.study.domain.Train;
import com.study.repository.TrainRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the {@link TrainRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link Train} entities in the repository.
 */
public class TrainRepositoryTest {
    private final int MAX_AMOUNT_SEATS_TRAIN = 120;
    private final int MIN_AMOUNT_SEATS_TRAIN = 40;
    private final int AVERAGE_AMOUNT_SEATS_TRAIN = 80;

    private Train train1;
    private Train train2;
    private Train train3;

    private static TrainRepository trainRepository;

    private Train createEntity(int amountOfSeats) {
        return new Train().amountOfSeats(amountOfSeats);
    }

    @BeforeEach
    void setUp() {
        trainRepository = new TrainRepository();

        // Initialize the repository and the age groups
        train1 = createEntity(MAX_AMOUNT_SEATS_TRAIN);
        train2 = createEntity(MIN_AMOUNT_SEATS_TRAIN);
        train3 = createEntity(AVERAGE_AMOUNT_SEATS_TRAIN);

        // Add the age groups to a list and save them in the repository
        List<Train> trains = new ArrayList<>();
        trains.add(train1);
        trains.add(train2);
        trains.add(train3);

        trainRepository.saveAll(trains);
    }

    @AfterAll
    public static void tearDown() {
        trainRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedAgeGroup() {
        // Check if the Train with ID 1 is correctly found
        assertTrue(trainRepository.existById(train1.getId()));
        assertEquals(train1, trainRepository.findById(train1.getId()).get());

        // Check if the Train with ID 2 is correctly found and has the correct type
        assertTrue(trainRepository.existById(train2.getId()));
        assertEquals(train2.getAmountOfSeats(), trainRepository.findById(train2.getId()).get().getAmountOfSeats());

        // Check if the Train with ID 2 is the same object as train2
        assertEquals(train2, trainRepository.findById(train2.getId()).get());

        // Ensure that Train with ID 2 is not the same as train3
        assertTrue(trainRepository.existById(train3.getId()));
        assertNotEquals(train3, trainRepository.findById(train2.getId()).get());

        // Ensure that Train with ID 1 is not the same as train3
        assertNotEquals(train3, trainRepository.findById(train1.getId()).get());
    }


    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(trainRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(trainRepository.existById(train1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(trainRepository.existById(train2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(trainRepository.existById(train3.getId()));
    }


    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){
        Train trainUpdate = new Train().amountOfSeats(MAX_AMOUNT_SEATS_TRAIN).id(train2.getId());

        // Update the Train with ID 2 and check if the update was successful
        assertTrue(trainRepository.updateId(train2.getId(), trainUpdate));

        // Ensure that updating with a null Train returns false
        assertFalse(trainRepository.updateId(train1.getId(), null));

        // Ensure that updating with null ID and null Train returns false
        assertFalse(trainRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid Train returns false
        assertFalse(trainRepository.updateId(null,trainUpdate));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        trainRepository.existById(train2.getId());
        trainRepository.deleteById(train2.getId());

        // Check that the element with ID 2 no longer exists
        trainRepository.existById(train2.getId());
        assertFalse(trainRepository.existById(train2.getId()));
    }


    @Test
    public void givenTrain_thenDeleteElInMap(){
        // Delete train3 from the repository
        assertTrue(trainRepository.existById(train3.getId()));
        trainRepository.delete(train3);

        // Verify that train3 no longer exists
        assertFalse(trainRepository.existById(train3.getId()));
    }


    @Test
    public void deleteAllElInMap(){
        assertTrue(trainRepository.existById(train3.getId()));
        assertTrue(trainRepository.existById(train2.getId()));
        assertTrue(trainRepository.existById(train1.getId()));

        // Delete all trains from the repository
        trainRepository.deleteAll();

        // Verify that none of the trains exist anymore
        assertFalse(trainRepository.existById(train3.getId()));
        assertFalse(trainRepository.existById(train2.getId()));
        assertFalse(trainRepository.existById(train1.getId()));
    }


    @Test
    public void givenListTrain_thenDeleteListElInMap(){
        // Create a list of trains to delete
        List<Train> trainsDelete = new ArrayList<>();
        trainsDelete.add(train1);
        trainsDelete.add(train2);

        // Delete the list of trains from the repository
        trainRepository.deleteAll(trainsDelete);

        // Verify that train3 still exists
        // Assertions.assertTrue(trainRepository.existById(train3.getId()));

        // Verify that train1 and train2 no longer exist
        assertFalse(trainRepository.existById(train2.getId()));
        assertFalse(trainRepository.existById(train1.getId()));
    }


    @Test
    public void givenElTrain_whenSaveElInMap_thenReturnTrain(){
        Train train4 = createEntity(MAX_AMOUNT_SEATS_TRAIN);

        trainRepository.save(train4);
        trainRepository.save(null);

        // Verify that train4 exists
        assertTrue(trainRepository.existById(train4.getId()));
    }


    @Test
    public void givenListElTrain_whenSaveListElInMap_thenReturnListElAgeTrain(){
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(trainRepository.existById(train1.getId()));
        assertTrue(trainRepository.existById(train2.getId()));

        Train train4 = createEntity(MAX_AMOUNT_SEATS_TRAIN);

        // Verify that the elements with IDs 4 do not exist
        assertFalse(trainRepository.existById(train4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(trainRepository.findById(train1.getId()).get(), train1);
        assertEquals(trainRepository.findById(train2.getId()).get(), train2);
    }
}
