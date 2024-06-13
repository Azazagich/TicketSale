package com.study.repositoryTests;

import com.study.domain.AgeGroup;
import com.study.domain.Station;
import com.study.repository.AgeGroupRepository;
import com.study.repository.StationRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains unit tests for the {@link StationRepository} class.
 * The tests cover various operations such as finding, checking existence,
 * updating, deleting, and saving {@link Station} entities in the repository.
 * */
public class StationRepositoryTest {

    private final String STATION_KYIV = "KYIV Station";
    private final String STATION_VINNYTSIA = "Vinnytsia Station";
    private final String STATION_LVIV = "Lviv Station";
    
    private Station station1;
    private Station station2;
    private Station station3;

    private static StationRepository stationRepository;

    private Station createEntity(String nameOfStation) {
        return new Station().nameOfStation(nameOfStation);
    }

    @BeforeEach
    void setUp() {
        stationRepository = new StationRepository();

        // Initialize the repository and the Station instances
        station1 = createEntity(STATION_KYIV);
        station2 = createEntity(STATION_VINNYTSIA);
        station3 = createEntity(STATION_LVIV);

        // Add the Station instances to a list and save them in the repository
        List<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);

        stationRepository.saveAll(stations);
    }

    @AfterEach
    void tearDown() {
        stationRepository.deleteAll();
    }


    @Test
    void givenId_whenFindByIdElInMap_thenReturnFoundedStation() {
        // Check if the Station with ID 1 is correctly found
        assertTrue( stationRepository.existById(station1.getId()));
        assertEquals(station1, stationRepository.findById(station1.getId()).get());

        // Check if the Station with ID 2 is correctly found and has the correct name
        assertTrue( stationRepository.existById(station2.getId()));
        assertEquals(station2.getNameOfStation(), stationRepository.findById(station2.getId()).get().getNameOfStation());

        // Check if the Station with ID 2 is the same object as station2
        assertEquals(station2, stationRepository.findById(station2.getId()).get());

        // Ensure that Station with ID 3 is not the same as station2
        assertTrue( stationRepository.existById(station3.getId()));
        assertNotEquals(station3, stationRepository.findById(station2.getId()).get());

        // Ensure that Station with ID 1 is not the same as station3
        assertNotEquals(station3, stationRepository.findById(station1.getId()).get());
    }

    @Test
    void findAll_thenReturnAllEntities(){
        assertTrue(stationRepository.findAll().containsAll(List.of(station1, station2, station3)));
        assertEquals(stationRepository.findAll(), List.of(station1, station2, station3));
    }

    
    @Test
    void givenId_whenCheckExistsByIdElInMap_thenReturnBooleanResult(){
        // Check if existence check with null ID returns false
        assertFalse(stationRepository.existById(null));

        // Check if existence check for ID 1 returns true
        assertTrue(stationRepository.existById(station1.getId()));

        // Check if existence check for ID 2 returns true
        assertTrue(stationRepository.existById(station2.getId()));

        // Check if existence check for ID 3 returns true
        assertTrue(stationRepository.existById(station3.getId()));
    }

    @Test
    void givenId_whenUpdateElInMap_thenReturnBooleanResult(){

        Station station = new Station().nameOfStation(STATION_VINNYTSIA).id(station2.getId());


        // Update the Station with ID 2 and check if the update was successful
        assertTrue(stationRepository.updateId(station.getId(), station));

        // Ensure that updating with a null Station returns false
        assertFalse(stationRepository.updateId(station1.getId(), null));

        // Ensure that updating with null ID and null Station returns false
        assertFalse(stationRepository.updateId(null,null));

        // Ensure that updating with null ID and a valid Station returns false
        assertFalse(stationRepository.updateId(null,station));
    }


    @Test
    public void givenId_thenDeleteElInMap(){
        // Delete the element with ID 2 and verify that it no longer exists
        stationRepository.existById(station2.getId());
        stationRepository.deleteById(station2.getId());

        // Check that the element with ID 2 no longer exists
        stationRepository.existById(station2.getId());
        assertFalse(stationRepository.existById(station2.getId()));
    }


    @Test
    public void givenStation_thenDeleteElInMap(){
        // Delete the station3 element from the repository
        assertTrue(stationRepository.existById(station3.getId()));
        stationRepository.delete(station3);


        // Verify that the element with ID 3 no longer exists
        assertFalse(stationRepository.existById(station3.getId()));
    }


    @Test
    public void deleteAllElInMap(){
        // Verify that all elements exist before deletion
        assertTrue(stationRepository.existById(station3.getId()));
        assertTrue(stationRepository.existById(station2.getId()));
        assertTrue(stationRepository.existById(station1.getId()));

        // Delete all elements from the repository
        stationRepository.deleteAll();

        // Verify that none of the elements exist anymore
        assertFalse(stationRepository.existById(station3.getId()));
        assertFalse(stationRepository.existById(station2.getId()));
        assertFalse(stationRepository.existById(station1.getId()));
    }

    @Test
    public void givenListStation_thenDeleteListElInMap() {
        // Create a list of elements to delete
        List<Station> stationDelete = new ArrayList<>();
        stationDelete.add(station1);
        stationDelete.add(station2);

        // Delete the list of elements from the repository
        stationRepository.deleteAll(stationDelete);

        // Verify that the element with ID 3 still exists
        assertTrue(stationRepository.existById(station3.getId()));

        // Verify that the elements with IDs 1 and 2 no longer exist
        assertFalse(stationRepository.existById(station2.getId()));
        assertFalse(stationRepository.existById(station1.getId()));
    }

    @Test
    public void givenElStation_whenSaveElInMap_thenReturnStation() {
        // Create a new Station instance
        Station station4 = createEntity(STATION_KYIV);

        // Save the new station in the repository
        stationRepository.save(station4);
        stationRepository.save(null);

        // Verify that the element with ID 4 exists
        assertTrue(stationRepository.existById(station4.getId()));
    }

    @Test
    public void givenListElStation_whenSaveListElInMap_thenReturnListElStation() {
        // Verify that the elements with IDs 1 and 2 exist
        assertTrue(stationRepository.existById(station1.getId()));
        assertTrue(stationRepository.existById(station2.getId()));

        // Create a new Station instance
        Station station4 = createEntity(STATION_KYIV);

        // Verify that the element with ID 4 does not exist before saving
        assertFalse(stationRepository.existById(station4.getId()));

        // Verify that the found elements with IDs 1 and 2 match the expected objects
        assertEquals(stationRepository.findById(station1.getId()).get(), station1);
        assertEquals(stationRepository.findById(station2.getId()).get(), station2);
    }
}
